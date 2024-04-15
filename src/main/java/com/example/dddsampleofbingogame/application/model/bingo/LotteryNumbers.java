package com.example.dddsampleofbingogame.application.model.bingo;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Value
@Accessors(fluent = true)
public class LotteryNumbers{
  @NonNull List<Integer> values;
}
