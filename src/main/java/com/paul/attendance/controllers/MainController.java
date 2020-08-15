package com.paul.attendance.controllers;

import com.paul.attendance.entity.DepartmentsEntity;
import com.paul.attendance.entity.WorkersEntity;
import com.paul.attendance.service.DepartmentsCRUD;
import com.paul.attendance.service.WorkersCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private WorkersCRUD workersCRUD;
    @Autowired
    private DepartmentsCRUD departmentsCRUD;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/attendance-table")
    public String table(Model model) {
        List<DepartmentsEntity> departments = departmentsCRUD.getAll();
        model.addAttribute("departments", departments);
        return "attendance-table";
    }

    @GetMapping("/dpt")
    public String dpt(Model model, @RequestParam int id) {
        table(model);
        DepartmentsEntity dep = departmentsCRUD.getById(id);
        List<WorkersEntity> workers = new ArrayList<>(dep.getWorkersById());
        model.addAttribute("workers", workers);
        return "dpt";
    }


}
