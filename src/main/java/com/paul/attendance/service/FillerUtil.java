package com.paul.attendance.service;

import com.paul.attendance.entity.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public abstract class FillerUtil {

    private static WorkersCRUD workersCrud = new WorkersCRUD();
    private static DepartmentsCRUD departmentsCrud = new DepartmentsCRUD();

    private static DaysCRUD daysCRUD = new DaysCRUD();

    public static void fillOneMonth() {
        if (!isFilled()) {
            List<DaysEntity> listOfDaysInDb = daysCRUD.getAll();

            Calendar calLast = Calendar.getInstance();

            Calendar nowCal = Calendar.getInstance();
            String currentYear = String.valueOf(nowCal.get(Calendar.YEAR));
            String currentMonth = String.valueOf(nowCal.get(Calendar.MONTH) + 1);

            int delta = 0;
            if (listOfDaysInDb.size() == 0) {
                calLast.setTime(Date.valueOf(currentYear + "-" + currentMonth + "-" + 1));
            } else {
                calLast.setTime(listOfDaysInDb.get(listOfDaysInDb.size() - 1).getDate());
                delta = 1;
            }


            for (int i = calLast.get(Calendar.DAY_OF_MONTH) + delta; i <= nowCal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
                DaysEntity entity = new DaysEntity();
                entity.setDate(Date.valueOf(currentYear + "-" +
                        currentMonth + "-" +
                        String.valueOf(i)));
                daysCRUD.save(entity);
            }
        }
    }

    private static boolean isFilled() {
        List<DaysEntity> listOfDaysInDb = daysCRUD.getAll();
        return !(listOfDaysInDb.size() < 31);
    }

    public static void fillWithTags() {
        Session session;

        for (int i = 0; i < TagsType.values().length; i++) {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            TagsEntity entity = new TagsEntity();
            entity.setTag(TagsType.values()[i].getCode());
            session.save(entity);
            session.flush();
            session.close();
        }
    }

    private static void addWorkers() {

        WorkersEntity w1 = new WorkersEntity();
        w1.setName("Petr");
        w1.setSurname("Petrov");
        w1.setBirth(Date.valueOf("1990-12-24"));
        w1.setAddress("Moscow");
        w1.setAge(30);
        w1.setProfession("Kotlin Developer");
        w1.setRemote(true);
        w1.setPhoto("photo3.jpg");
        w1.setDepartmentsByDepartment(departmentsCrud.getByName("HR"));

        WorkersEntity w2 = new WorkersEntity();
        w2.setName("Ivan");
        w2.setSurname("Ivanov");
        w2.setBirth(Date.valueOf("1992-03-06"));
        w2.setAddress("Kazan");
        w2.setAge(35);
        w2.setProfession("Manager");
        w2.setRemote(true);
        w2.setPhoto("photo5.jpg");
        w2.setDepartmentsByDepartment(departmentsCrud.getByName("Bookkeeping"));

        workersCrud.save(w1);
        workersCrud.save(w2);
    }

    private static void addDepartments() {
        DepartmentsEntity d1 = new DepartmentsEntity();
        d1.setName("HR");
        DepartmentsEntity d2 = new DepartmentsEntity();
        d2.setName("Bookkeeping");

        departmentsCrud.save(d1);
        departmentsCrud.save(d2);
    }


}
