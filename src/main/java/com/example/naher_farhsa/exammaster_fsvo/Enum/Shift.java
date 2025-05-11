package com.example.naher_farhsa.exammaster_fsvo.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Shift {
    MORNING("9AM-12PM"),
    AFTERNOON("1PM-4PM");
    private String shiftTime;
}
