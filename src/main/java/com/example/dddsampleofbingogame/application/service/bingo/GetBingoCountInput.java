package com.example.dddsampleofbingogame.application.service.bingo;

import lombok.NonNull;

import java.util.List;

public record GetBingoCountInput(
  @NonNull Integer maxSideLength,
  @NonNull List<BingoNumber> bingoNumbers,
  @NonNull List<Integer> lotteryNumbers
) {
  public record BingoNumber(
    @NonNull Integer row,
    @NonNull Integer column,
    @NonNull Integer number
  ) {
  }
}
