package com.paul.attendance.service;

import com.paul.attendance.entity.DaysEntity;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

public class DaysCRUD {

    public void save(DaysEntity day) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(day); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public List<DaysEntity> getAll(){
        Session session = HibernateUtil.getSession();
        return session.createCriteria(DaysEntity.class).list();
    }


    public DaysEntity getByDate(String str) {
        Date date = Date.valueOf(str);
        List<DaysEntity> list = getAll();
        DaysEntity resultEntity = null;

        for (DaysEntity entity : list) {
            if (entity.getDate().equals(date)) {
                resultEntity = entity;
            }
        }

        return resultEntity;
    }
}
