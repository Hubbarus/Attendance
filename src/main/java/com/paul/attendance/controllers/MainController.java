package com.paul.attendance.controllers;

import com.paul.attendance.entity.DayEntity;
import com.paul.attendance.entity.DepartmentsEntity;
import com.paul.attendance.entity.EmployeeEntity;
import com.paul.attendance.service.DepartmentsCRUD;
import com.paul.attendance.service.EmployeeCRUD;
import com.paul.attendance.service.DayCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String dpt(Model model, @RequestParam int deptId, @RequestParam String month) {
        List<DepartmentsEntity> departments = departmentsCRUD.getAll();

        DepartmentsEntity dep = departmentsCRUD.getById(deptId);
        List<EmployeeEntity> employees = new ArrayList<>(dep.getEmployeesById());

        Collections.sort(employees, new Comparator<EmployeeEntity>() {
            @Override
            public int compare(EmployeeEntity o1, EmployeeEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        LinkedHashMap<EmployeeEntity, LinkedList<DayEntity>> employeeEventMap = new LinkedHashMap<>();

        for (EmployeeEntity employee : employees) {
            employeeEventMap.put(employee, dayCRUD.getEventsByEmployeeAndMonth(employee, month));
        }

        List<DayEntity> days = dayCRUD.getByMonth(month);

        model.addAttribute("days", days);
        model.addAttribute("departments", departments);
        model.addAttribute("events", employeeEventMap);
        model.addAttribute("currentMonth", StringUtils.capitalize(month));
        model.addAttribute("departmentId", deptId);
        return "dpt";
    }


}
