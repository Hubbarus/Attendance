package com.paul.attendance.service;

import com.paul.attendance.entity.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public abstract class FillerUtil {

    private static EmployeeCRUD employeeCrud = new EmployeeCRUD();
    private static DepartmentsCRUD departmentsCrud = new DepartmentsCRUD();
    private static ScheduleCRUD scheduleCRUD = new ScheduleCRUD();
    /*public static void fillOneMonth() {
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
*/

    public static void addEmployees() {

        EmployeeEntity w1 = new EmployeeEntity();
        w1.setName("Vladimir");
        w1.setSurname("Kashin");
        w1.setBirth(Date.valueOf("1982-08-01"));
        w1.setAddress("Moscow");
        w1.setAge(35);
        w1.setProfession("Kotlin Developer");
        w1.setRemote(true);
        w1.setPhoto("photo7.jpg");
        w1.setDepartmentsByDepartment(departmentsCrud.getByName("HR"));

        EmployeeEntity w2 = new EmployeeEntity();
        w2.setName("Eugeniy");
        w2.setSurname("Romanov");
        w2.setBirth(Date.valueOf("1984-07-22"));
        w2.setAddress("Kiev");
        w2.setAge(44);
        w2.setProfession("Manager");
        w2.setRemote(true);
        w2.setPhoto("photo99.jpg");
        w2.setDepartmentsByDepartment(departmentsCrud.getByName("Bookkeeping"));

        employeeCrud.save(w1);
        employeeCrud.save(w2);
    }

    public static void addDepartments() {
        DepartmentsEntity d1 = new DepartmentsEntity();
        d1.setName("HR");
        DepartmentsEntity d2 = new DepartmentsEntity();
        d2.setName("Bookkeeping");

        departmentsCrud.save(d1);
        departmentsCrud.save(d2);
    }

    public static void fillSchedule() {
        List<EmployeeEntity> employeeEntities = employeeCrud.getAll();
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.valueOf("2020-01-01"));

        Calendar tmp = Calendar.getInstance();
        tmp.setTime(Date.valueOf("2021-01-01"));

        for (int i = 0; i < employeeEntities.size(); i++) {
            EmployeeEntity currentEmployee = employeeEntities.get(i);

            while (cal.before(tmp)) {
                ScheduleEntity entity = new ScheduleEntity();
                Date date = new Date(cal.getTime().getTime());
                entity.setDate(date);
                entity.setEmployeeByEmployee(currentEmployee);
                entity.setTag(TagsType.DAY_OFF.getCode());
                scheduleCRUD.save(entity);
                cal.add(Calendar.DATE, 1);
            }

            cal.setTime(Date.valueOf("2020-01-01"));
        }
    }
}
