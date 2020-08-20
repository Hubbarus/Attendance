package com.paul.attendance.service;

import com.paul.attendance.entity.DayEntity;
import com.paul.attendance.entity.EmployeeEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Component
public class DayCRUD {

    public void save(DayEntity day) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(day); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(DayEntity day) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(day);
        session.flush();
        session.close();
    }

    public void update(DayEntity day) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(day);
        session.flush();
        session.close();
    }

    public List<DayEntity> getAll() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<DayEntity> cq = session.getCriteriaBuilder().createQuery(DayEntity.class);
        Root<DayEntity> rootEntry = cq.from(DayEntity.class);
        CriteriaQuery<DayEntity> all = cq.select(rootEntry);

        TypedQuery<DayEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public List<DayEntity> getByMonth(String month) {
        List<DayEntity> allDays = getAll();
        List<DayEntity> resultList = new LinkedList<>();

        for (DayEntity currentDay : allDays) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDay.getDate());
            String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase();
            if (month.toUpperCase().equals(monthName)) {
                resultList.add(currentDay);
            }
        }

        return resultList;
    }

    public List<DayEntity> getListByEmployee(EmployeeEntity employee) {
        List<DayEntity> days = getAll();
        List<DayEntity> resultList = new LinkedList<>();

        for (DayEntity day : days) {
            if (day.getEmployeeByEmployee().equals(employee)) {
                resultList.add(day);
            }
        }

        return resultList;
    }

    public DayEntity getByDateAndEmployee(EmployeeEntity employee, String inputDate) {
        Date date = Date.valueOf(inputDate);
        List<DayEntity> entities = getListByEmployee(employee);
        DayEntity resultEntity = null;

        for (DayEntity entity : entities) {
            if (entity.getDate().equals(date)) {
                resultEntity = entity;
            }
        }

        if (resultEntity == null) throw new NoSuchElementException();

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

    public LinkedList<String> getEventsByEmployee(EmployeeEntity employee) {
        LinkedList<String> resultList = new LinkedList<>();
        List<DayEntity> allDays = getAll();

        for (DayEntity currentDay : allDays) {
            if (currentDay.getEmployeeByEmployee().equals(employee)) {
                resultList.add(currentDay.getEvent());
            }
        }

        return resultList;
    }

    public LinkedList<DayEntity> getEventsByEmployeeAndMonth(EmployeeEntity employee, String month) {
        LinkedList<DayEntity> resultList = new LinkedList<>();
        List<DayEntity> allDays = getByMonth(month);

        for (DayEntity day : allDays) {
            if (day.getEmployeeByEmployee().equals(employee)) {
                resultList.add(day);
            }
        }

        return resultList;
    }
}
