package com.paul.attendance.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Tags", schema = "attendanceDB", catalog = "")
public class TagsEntity {
    private int id;
    private String tag;
    private Collection<DaysEntity> daysById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag", nullable = false)
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

        TagsEntity that = (TagsEntity) o;

        if (id != that.id) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tagsByTag")
    public Collection<DaysEntity> getDaysById() {
        return daysById;
    }

    public void setDaysById(Collection<DaysEntity> daysById) {
        this.daysById = daysById;
    }
}
