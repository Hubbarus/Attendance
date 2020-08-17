package com.paul.attendance.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Schedule", schema = "attendanceDB", catalog = "")
public class ScheduleEntity {
    private int id;
    private Date date;
    private String tag;
    private EmployeeEntity employeeByEmployee;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = -1)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getEmployeeByEmployee() {
        return employeeByEmployee;
    }

    public void setEmployeeByEmployee(EmployeeEntity employeeByEmployee) {
        this.employeeByEmployee = employeeByEmployee;
    }
}
