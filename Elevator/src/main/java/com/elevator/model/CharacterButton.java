package com.elevator.model;

import com.elevator.enums.ButtonTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class CharacterButton {
    private final Character buttonSymbol;
    private final ButtonTypeEnum buttonType;

}
