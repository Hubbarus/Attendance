package com.paul.attendance.controllers;

import com.paul.attendance.entity.DepartmentsEntity;
import com.paul.attendance.entity.EmployeeEntity;
import com.paul.attendance.entity.DayEntity;
import com.paul.attendance.service.DepartmentsCRUD;
import com.paul.attendance.service.EmployeeCRUD;
import com.paul.attendance.service.DayCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    private EmployeeCRUD employeeCRUD;
    @Autowired
    private DepartmentsCRUD departmentsCRUD;
    @Autowired
    private DayCRUD dayCRUD;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/dpt")
    public String dpt(Model model, @RequestParam int deptId) {
        List<DepartmentsEntity> departments = departmentsCRUD.getAll();

        DepartmentsEntity dep = departmentsCRUD.getById(deptId);
        List<EmployeeEntity> employees = new ArrayList<>(dep.getEmployeesById());
        Collections.sort(employees, new Comparator<EmployeeEntity>() {
            @Override
            public int compare(EmployeeEntity o1, EmployeeEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        List<DayEntity> cols = dayCRUD.getAll();
        Collections.sort(cols, new Comparator<>() {
            @Override
            public int compare(DayEntity o1, DayEntity o2) {
                return o1.getEmployeeByEmployee().getName().compareTo(o2.getEmployeeByEmployee().getName());
            }
        });

        model.addAttribute("departments", departments);
        model.addAttribute("employees", employees);
        model.addAttribute("cols", cols);
        return "dpt";
    }


}
