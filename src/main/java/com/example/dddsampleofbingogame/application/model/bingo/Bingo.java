package com.example.dddsampleofbingogame.application.model.bingo;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "reconstruct")
@Accessors(fluent = true)
public class Bingo{
  @NonNull BingoNumbers numbers;
  @NonNull LotteryNumbers lotteryNumbers;
}
