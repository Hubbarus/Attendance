package com.paul.attendance.service;

import com.paul.attendance.entity.DepartmentsEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentsCRUD {

    public void save(DepartmentsEntity department) {
        Session session = HibernateUtil.getSession(); //открываем сессию
        session.beginTransaction();
        session.save(department); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(DepartmentsEntity department) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(department);
        session.flush();
        session.close();
    }

    public DepartmentsEntity getById(Integer id) {
        Session session = HibernateUtil.getSession();
        DepartmentsEntity department = session.get(DepartmentsEntity.class, id);
        return department;
    }

    public List<DepartmentsEntity> getAll(){
        Session session = HibernateUtil.getSession();
        return session.createCriteria(DepartmentsEntity.class).list();
    }

    public DepartmentsEntity getByName(String name) throws IllegalArgumentException {
        Session session = HibernateUtil.getSession();
        List<DepartmentsEntity> list = getAll();

        DepartmentsEntity resultEntity = null;
        for (DepartmentsEntity entity : list) {
            if (entity.getName().equals(name)) {
                resultEntity = entity;
            }
        }

        if (resultEntity == null) throw new IllegalArgumentException();

        return resultEntity;
    }
}
