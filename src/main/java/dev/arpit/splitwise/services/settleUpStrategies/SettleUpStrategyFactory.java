package dev.arpit.splitwise.services.settleUpStrategies;

public class SettleUpStrategyFactory {
  public static ISettleUpStrategy getSettleUpStrategy() {
    return new MaxLenderBorrowerMatchSettleUpStrategy();
  }
}