package com.example.dddsampleofbingogame.application.service.bingo;

import com.example.dddsampleofbingogame.application.model.bingo.*;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class GetBingoCountService {
  public GetBingoCountOutput getBingoCount(@NonNull final GetBingoCountInput input) {
    val maxSideLength = new MaxSideLength(input.maxSideLength());
    val lotteryNumbers = new LotteryNumbers(input.lotteryNumbers());
    val bingoNumbers = new BingoNumbers(
      maxSideLength,
      input.bingoNumbers().stream()
        .map(bingoNumber -> BingoNumber.create(
          bingoNumber.row(),
          bingoNumber.column(),
          bingoNumber.number()
        ))
        .toList()
    ).drawLottery(lotteryNumbers);

    val bingo = Bingo.reconstruct(
      bingoNumbers,
      lotteryNumbers
    );

    return new GetBingoCountOutput(countBingo(bingo));
  }

  public int countBingo(@NonNull final Bingo bingo) {
    return (int) Stream.of(
        bingo.numbers().countRow.get(),
        bingo.numbers().countColumn.get(),
        bingo.numbers().countDiagonal.get(),
        bingo.numbers().countReverseDiagonal.get()
      )
      .mapToLong(Long::longValue)
      .sum();
  }
}
