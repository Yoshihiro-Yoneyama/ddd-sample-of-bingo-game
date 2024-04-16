package com.example.dddsampleofbingogame.presentation;

import com.example.dddsampleofbingogame.application.service.bingo.GetBingoCountInput;
import com.example.dddsampleofbingogame.application.service.bingo.GetBingoCountService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class GetBingoCountController {
  @NonNull GetBingoCountService getBingoCountService;

  @PostMapping("api")
  public GetBingoCountResponse get(@RequestBody final GetBingoCountRequest request) {
    final var output = getBingoCountService.getBingoCount(
      new GetBingoCountInput(
        request.maxSideLength(),
        request.bingoNumbers()
          .stream()
          .map(bingoNumber -> new GetBingoCountInput.BingoNumber(
            bingoNumber.row(),
            bingoNumber.column(),
            bingoNumber.number()
          ))
          .toList(),
        request.lotteryNumbers()
      )
    );

    return new GetBingoCountResponse(output.count());
  }
}
