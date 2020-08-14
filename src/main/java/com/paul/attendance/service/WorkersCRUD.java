package com.paul.attendance.service;

import com.paul.attendance.entity.WorkersEntity;
import org.hibernate.Session;

import java.util.List;

public class WorkersCRUD {

    public void save(WorkersEntity workers) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(workers); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(WorkersEntity workers) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(workers);
        session.flush();
        session.close();
    }

    public List<WorkersEntity> getAll(){
        Session session = HibernateUtil.getSession();
        return session.createCriteria(WorkersEntity.class).list();
    }

    public WorkersEntity getById(Integer id) {
        Session session = HibernateUtil.getSession();
        WorkersEntity workers = session.get(WorkersEntity.class, id);
        return workers;
    }
}
