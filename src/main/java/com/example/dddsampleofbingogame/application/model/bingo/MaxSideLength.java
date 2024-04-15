package com.example.dddsampleofbingogame.application.model.bingo;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class MaxSideLength {
  @NonNull Integer value;

  public MaxSideLength(int value) {
    if (value <= 1) throw new IllegalArgumentException("value must be greater than 1");
    this.value = value;
  }
}
