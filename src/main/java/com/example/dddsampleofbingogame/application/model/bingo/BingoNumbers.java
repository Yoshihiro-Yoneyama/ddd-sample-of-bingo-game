package com.example.dddsampleofbingogame.application.model.bingo;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Value
@Accessors(fluent = true)
public class BingoNumbers {
  @NonNull MaxSideLength maxSideLength;
  @NonNull List<BingoNumber> bingoNumbers;

  public BingoNumbers(
    @NonNull final MaxSideLength maxSideLength,
    @NonNull final List<BingoNumber> bingoNumbers
  ) {
    if (bingoNumbers.size() != maxSideLength.value() * maxSideLength.value()) throw new IllegalArgumentException("bingoNumbers size is invalid");
    this.maxSideLength = maxSideLength;
    this.bingoNumbers = bingoNumbers;
  }

  public Supplier<Long> countRow = () ->
    IntStream.range(0, this.maxSideLength().value())
      .filter(i -> this.bingoNumbers().stream()
        .filter(bingoNumber -> bingoNumber.hitStatus() && bingoNumber.row() == i)
        .count() == this.maxSideLength().value()
      )
      .count();

  public Supplier<Long> countColumn = () ->
    IntStream.range(0, this.maxSideLength().value())
      .filter(i -> this.bingoNumbers().stream()
        .filter(bingoNumber -> bingoNumber.hitStatus() && bingoNumber.column() == i)
        .count() == this.maxSideLength().value()
      )
      .count();

  public Supplier<Long> countDiagonal = () ->
    this.bingoNumbers().stream()
      .filter(v -> v.hitStatus() && v.row().equals(v.column()))
      .count() == this.maxSideLength().value() ? 1L : 0L;

  public Supplier<Long> countReverseDiagonal = () ->
    this.bingoNumbers().stream()
      .filter(v -> v.hitStatus() && v.row() + v.column() == this.maxSideLength().value() - 1)
      .count() == this.maxSideLength().value() ? 1L : 0L;


  public BingoNumbers drawLottery(LotteryNumbers lotteryNumbers) {
    if (lotteryNumbers == null) throw new IllegalArgumentException("lotteryNumbers is required");
    lotteryNumbers.values()
      .forEach(n -> this.bingoNumbers.forEach(bingoNumber -> bingoNumber.changeHitStatus(n)));
    return this;
  }
}
