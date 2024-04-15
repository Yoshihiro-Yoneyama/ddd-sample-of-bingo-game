package com.example.dddsampleofbingogame.application.model.bingo;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class BingoNumber {
  @NonNull Integer row;
  @NonNull Integer column;
  @NonNull Integer number;
  @NonNull @With(AccessLevel.PRIVATE) Boolean hitStatus;

  private BingoNumber(
    final@NonNull Integer row,
    final @NonNull Integer column,
    final @NonNull Integer number,
    final @NonNull Boolean hitStatus
  ) {
    if (row < 0) throw new IllegalArgumentException("row must be greater than or equal to 1");
    if (column < 0) throw new IllegalArgumentException("column must be greater than or equal to 1");
    if (number < 0) throw new IllegalArgumentException("number must be greater than or equal to 0");
    this.row = row;
    this.column = column;
    this.number = number;
    this.hitStatus = hitStatus;
  }

  public static BingoNumber create(
    final @NonNull Integer row,
    final @NonNull Integer column,
    final @NonNull Integer number
  ) {
    return new BingoNumber(
      row,
      column,
      number,
      number == 0
    );
  }

  public BingoNumber changeHitStatus(final @NonNull Integer number) {
    return this.number.equals(number) ? this.withHitStatus(true) : this;
  }
}