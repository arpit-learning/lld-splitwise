package dev.arpit.splitwise.services.settleUpStrategies;

import dev.arpit.splitwise.models.Group;
import dev.arpit.splitwise.models.SuggestedTransaction;

import java.util.List;

public interface ISettleUpStrategy {
  List<SuggestedTransaction> settleUp(Group group);
}