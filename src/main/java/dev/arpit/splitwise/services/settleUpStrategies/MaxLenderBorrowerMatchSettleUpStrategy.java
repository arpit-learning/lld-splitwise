package dev.arpit.splitwise.services.settleUpStrategies;

import dev.arpit.splitwise.models.*;

import java.util.*;

public class MaxLenderBorrowerMatchSettleUpStrategy implements ISettleUpStrategy {

  @Override
  public List<SuggestedTransaction> settleUp(Group group) {
    //Calculating the outstanding
    HashMap<User, Double> userLedgerMap = new HashMap<>();

    List<Expense> expenses = group.getExpenses();
    for(Expense expense : expenses) {
      List<ExpenseLedger> expenseLedgers = expense.getExpenseLedgers();
      if(expenseLedgers != null) {
        for (ExpenseLedger expenseLedger : expenseLedgers) {
          User user = expenseLedger.getUser();
          if (!userLedgerMap.containsKey(user)) {
            userLedgerMap.put(user, 0.0);
          }
          double delta = expenseLedger.getExpenseType() == ExpenseType.PAID ? expenseLedger.getAmount() : - expenseLedger.getAmount();
          userLedgerMap.put(
              user,
              userLedgerMap.get(user) + delta
          );
        }
      }
    }

    // min Heap for borrowers and max heap for lenders
    PriorityQueue<UserLedgerPair> borrowerHeap = new PriorityQueue<>(
        Comparator.comparingDouble(UserLedgerPair::getLedger));
    PriorityQueue<UserLedgerPair> lenderHeap = new PriorityQueue<>(
        Comparator.comparingDouble(UserLedgerPair::getLedger).reversed());

    for(Map.Entry<User, Double> entry : userLedgerMap.entrySet()) {
      UserLedgerPair pair = new UserLedgerPair(entry.getKey(), entry.getValue());
      if(entry.getValue() > 0.0) {
        lenderHeap.add(pair);
      } else if(entry.getValue() < 0.0) {
        borrowerHeap.add(pair);
      }
    }

    // Start settling by picking the min from borrowers and max from lenders
    List<SuggestedTransaction> suggestedTransactions = new ArrayList<>();
    while (!borrowerHeap.isEmpty() && !lenderHeap.isEmpty()) {
      UserLedgerPair borrowerPair = borrowerHeap.remove();
      UserLedgerPair lenderPair = lenderHeap.remove();
      double amount = Math.min(lenderPair.getLedger(), -borrowerPair.getLedger());
      SuggestedTransaction t = new SuggestedTransaction(
          borrowerPair.getUser(),
          lenderPair.getUser(),
          amount,
          group
      );
      suggestedTransactions.add(t);
      lenderPair.setLedger(lenderPair.getLedger() - amount);
      borrowerPair.setLedger(borrowerPair.getLedger() + amount);
      if(lenderPair.getLedger() > 0) {
        lenderHeap.add(lenderPair);
      } else if(borrowerPair.getLedger() < 0) {
        borrowerHeap.add(borrowerPair);
      }
    }

    return suggestedTransactions;
  }
}
