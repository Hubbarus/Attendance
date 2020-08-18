package com.paul.attendance;

import com.paul.attendance.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        DayCRUD crud = new DayCRUD();
        LinkedHashMap<String, Integer> map = crud.getListOfMonthsAndDates();
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
