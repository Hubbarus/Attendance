package com.paul.attendance.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Day", schema = "attendanceDB", catalog = "")
public class DayEntity {
    private int id;
    //@DateTimeFormat(pattern = "MM")
    private Date date;
    private String event;
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
    @Column(name = "event", nullable = false, length = -1)
    public String getEvent() {
        return event;
    }

    public void setEvent(String tag) {
        this.event = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayEntity that = (DayEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
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
