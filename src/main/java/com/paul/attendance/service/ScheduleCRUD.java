package com.paul.attendance.service;

import com.paul.attendance.entity.ScheduleEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Component
public class ScheduleCRUD {

    public void save(ScheduleEntity day) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(day); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(ScheduleEntity day) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(day);
        session.flush();
        session.close();
    }

    public void update(ScheduleEntity schedule) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(schedule);
        session.flush();
        session.close();
    }

    public List<ScheduleEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createCriteria(ScheduleEntity.class).list();
    }

    public ScheduleEntity getByDate(String str) {
        Date date = Date.valueOf(str);
        List<ScheduleEntity> list = getAll();
        ScheduleEntity resultEntity = null;

        for (ScheduleEntity entity : list) {
            if (entity.getDate().equals(date)) {
                resultEntity = entity;
            }
        }
        return resultEntity;
    }

    public LinkedHashMap<String, Integer> getListOfMonthsAndDates() {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf("2020-01-01"));
        for (int i = 0; i < 12; i++) {
            result.put(Month.of(i + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            calendar.add(Calendar.MONTH, 1);
        }

        return result;
    }

}
