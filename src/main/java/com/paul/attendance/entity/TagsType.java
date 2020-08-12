package com.paul.attendance.entity;

public enum TagsType {
    WORKING_DAY ("Я"),
    ABSENT("Н"),
    DAY_OFF("В"),
    WORK_HOLIDAY("Рв"),
    ILL("Б"),
    BUSINESS_TRIP("К"),
    PAID_VACATION("ОТ"),
    NOT_PAID_VACATION("До"),
    CLEANING_DAY("Хд"),
    STUDING_VACATION("У"),
    CHILD_VACATION("Ож");

    TagsType(String s) {
    }
}
