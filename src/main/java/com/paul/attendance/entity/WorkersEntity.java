package com.paul.attendance.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Workers", schema = "attendanceDB", catalog = "")
public class WorkersEntity {
    private int id;
    private String photo;
    private String name;
    private String surname;
    private Date birth;
    private int age;
    private String profession;
    private byte remote;
    private String address;
    private Collection<DaysEntity> daysById;
    private DepartmentsEntity departmentsByDepartmentId;

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
    @Column(name = "surname", nullable = false, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "profession", nullable = false, length = -1)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "remote", nullable = false)
    public byte getRemote() {
        return remote;
    }

    public void setRemote(byte remote) {
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
        WorkersEntity that = (WorkersEntity) o;
        return id == that.id &&
                age == that.age &&
                remote == that.remote &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(birth, that.birth) &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(address, that.address) &&
                Objects.equals(daysById, that.daysById) &&
                Objects.equals(departmentsByDepartmentId, that.departmentsByDepartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo, name, surname, birth, age, profession, remote, address, daysById, departmentsByDepartmentId);
    }

    @OneToMany(mappedBy = "workersByWorker")
    public Collection<DaysEntity> getDaysById() {
        return daysById;
    }

    public void setDaysById(Collection<DaysEntity> daysById) {
        this.daysById = daysById;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public DepartmentsEntity getDepartmentsByDepartmentId() {
        return departmentsByDepartmentId;
    }

    public void setDepartmentsByDepartmentId(DepartmentsEntity departmentsByDepartmentId) {
        this.departmentsByDepartmentId = departmentsByDepartmentId;
    }
}
