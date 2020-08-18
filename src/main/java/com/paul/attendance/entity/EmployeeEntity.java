package com.paul.attendance.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Employee", schema = "attendanceDB", catalog = "")
public class EmployeeEntity {
    private int id;
    private String photo;
    private String name;
    private String lastname;
    private Date birth;
    private String position;
    private boolean remote;
    private String address;
    private DepartmentsEntity departmentsByDepartment;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "photo", nullable = false, length = -1)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = -1)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    @Basic
    @Column(name = "birth", nullable = false)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "position", nullable = false, length = -1)
    public String getPosition() {
        return position;
    }

    public void setPosition(String profession) {
        this.position = profession;
    }

    @Basic
    @Column(name = "remote", nullable = false)
    public boolean getRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    @Basic
    @Column(name = "address", nullable = false, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (remote != that.remote) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (birth != null ? !birth.equals(that.birth) : that.birth != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name +
                " " + lastname;
    }

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id", nullable = false)
    public DepartmentsEntity getDepartmentsByDepartment() {
        return departmentsByDepartment;
    }

    public void setDepartmentsByDepartment(DepartmentsEntity departmentsByDepartment) {
        this.departmentsByDepartment = departmentsByDepartment;
    }
}
