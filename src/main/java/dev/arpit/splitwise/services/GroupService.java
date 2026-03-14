package dev.arpit.splitwise.services;

import dev.arpit.splitwise.dtos.ResponseCode;
import dev.arpit.splitwise.exceptions.InvalidGroupUserException;
import dev.arpit.splitwise.exceptions.InvalidGroupIdException;
import dev.arpit.splitwise.exceptions.NoGroupUserException;
import dev.arpit.splitwise.exceptions.UnAuthorizedAccessException;
import dev.arpit.splitwise.models.*;
import dev.arpit.splitwise.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GroupService implements IGroupService {
  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private IGroupUserService iGroupUserService;
  @Autowired
  private IExpenseService iExpenseService;

  @Override
  public Group save(Group group) {
    return groupRepository.save(group);
  }

  @Override
  public Group findById(long groupId) throws InvalidGroupIdException {
    return groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupIdException(ResponseCode.SW_ERR_400, "group with groupId: " + groupId + " not found", "Group not found. Please pass correct groupId in the payload."));
  }

  @Override
  public void delete(Group group) {
    groupRepository.delete(group);
  }

  @Override
  @Transactional
  public Group createGroup(String groupName, String description, User admin) throws InvalidGroupUserException {
    Group group = new Group(groupName, description, admin, null);
    group = this.save(group);

    GroupUser groupUser = new GroupUser(group, admin, admin, GroupUserType.ADMIN);
    iGroupUserService.save(groupUser);

    return group;
  }

  @Override
  @Transactional
  public Group deleteGroup(long groupId, User admin) throws InvalidGroupIdException, UnAuthorizedAccessException, NoGroupUserException {
    Group group = this.findById(groupId);
    boolean isAdmin = iGroupUserService.isUserAdmin(group, admin);
    if(!isAdmin) {
      throw new UnAuthorizedAccessException(ResponseCode.SW_ERR_403, "admin with id " + admin.getId() + " is not an admin of the group", "Please pass correct admin id.");
    }

    List<GroupUser> groupUsers = iGroupUserService.findAllByGroup(group);

    iGroupUserService.deleteAll(groupUsers);
    this.delete(group);
    return group;
  }

  @Override
  public List<SuggestedTransaction> settleUp (long groupId, User admin) throws InvalidGroupIdException, NoGroupUserException, UnAuthorizedAccessException {
    Group group = this.findById(groupId);
    if(!iGroupUserService.isUserAdmin(group, admin)) {
      throw new UnAuthorizedAccessException(
          ResponseCode.SW_ERR_400,
          "User " + admin.getId() + " is unauthorized to settle transactions",
          "You are unauthorized to settle up expenses."
      );
    }

    Map<User, Double> ledgerMap = new HashMap<>();
    List<GroupUser> groupUsers = iGroupUserService.findAllByGroup(group);
    if (groupUsers != null) {
      for (GroupUser user : groupUsers) {
        ledgerMap.put(user.getUser(), 0.0);
      }
    }

    List<Expense> groupExpenses = iExpenseService.findAllByGroup(group);
    for (Expense groupExpense : groupExpenses) {
      List<ExpenseLedger> expenseLedgers = groupExpense.getExpenseLedgers();
      if (expenseLedgers != null) {
        for (ExpenseLedger expenseLedger : expenseLedgers) {
          double delta = expenseLedger.getExpenseType() == ExpenseType.PAID ? expenseLedger.getAmount() : -expenseLedger.getAmount();
          ledgerMap.put(expenseLedger.getUser(), ledgerMap.getOrDefault(expenseLedger.getUser(), 0.0) + delta);
        }
      }
    }

    return getSuggestedTransactions(ledgerMap, group);
  }

  private List<SuggestedTransaction> getSuggestedTransactions (Map<User, Double> ledgerMap, Group group) {
    // min heap for borrowers
    PriorityQueue<UserLedgerPair> borrowersHeap = new PriorityQueue<>(
        Comparator.comparingDouble(UserLedgerPair::getLedger)
    );
    // max heap for lenders
    PriorityQueue<UserLedgerPair> lendersHeap = new PriorityQueue<>(
        Comparator.comparingDouble(UserLedgerPair::getLedger).reversed()
    );

    for(Map.Entry<User, Double> entry: ledgerMap.entrySet()) {
      UserLedgerPair pair = new UserLedgerPair(entry.getKey(), entry.getValue());
      if(pair.getLedger() > 0) {
        lendersHeap.add(pair);
      } else if(pair.getLedger() < 0) {
        borrowersHeap.add(pair);
      }
    }
    List<SuggestedTransaction> suggestedTransactions = new ArrayList<>();
    while(!lendersHeap.isEmpty() && !borrowersHeap.isEmpty()) {
      UserLedgerPair lender = lendersHeap.poll();
      UserLedgerPair borrower = borrowersHeap.poll();
      double amount = Math.min(lender.getLedger(), -borrower.getLedger());
      SuggestedTransaction suggestedTransaction = new SuggestedTransaction(
          borrower.getUser(),
          lender.getUser(),
          amount,
          group
      );
      suggestedTransactions.add(suggestedTransaction);
      lender.setLedger(lender.getLedger() - amount);
      borrower.setLedger(borrower.getLedger() + amount);
      if(lender.getLedger() > 0) {
        lendersHeap.add(lender);
      } else if(borrower.getLedger() < 0) {
        borrowersHeap.add(borrower);
      }
    }
    return suggestedTransactions;
  }
}
