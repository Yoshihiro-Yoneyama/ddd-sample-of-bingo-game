package com.example.dddsampleofbingogame.presentation;

import lombok.NonNull;

import java.util.List;

public record GetBingoCountRequest(
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
