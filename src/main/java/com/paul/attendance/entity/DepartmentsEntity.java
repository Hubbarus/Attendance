package com.paul.attendance.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Departments", schema = "attendanceDB", catalog = "")
public class DepartmentsEntity {
    private int id;
    private String name;
    private Collection<WorkersEntity> workersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentsEntity that = (DepartmentsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(workersById, that.workersById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, workersById);
    }

    @OneToMany(mappedBy = "departmentsByDepartmentId")
    public Collection<WorkersEntity> getWorkersById() {
        return workersById;
    }

    public void setWorkersById(Collection<WorkersEntity> workersById) {
        this.workersById = workersById;
    }
}
