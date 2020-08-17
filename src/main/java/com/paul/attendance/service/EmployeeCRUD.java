package com.paul.attendance.service;

import com.paul.attendance.entity.EmployeeEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeCRUD {

    public void save(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(employee); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(employee);
        session.flush();
        session.close();
    }

    public void update(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(employee);
        session.flush();
        session.close();
    }

    public List<EmployeeEntity> getAll(){
        Session session = HibernateUtil.getSession();
        return session.createCriteria(EmployeeEntity.class).list();
    }

    public EmployeeEntity getById(Integer id) {
        Session session = HibernateUtil.getSession();
        EmployeeEntity workers = session.get(EmployeeEntity.class, id);
        return workers;
    }
}
