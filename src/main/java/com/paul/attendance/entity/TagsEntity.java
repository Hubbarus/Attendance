package com.paul.attendance.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Tags", schema = "attendanceDB", catalog = "")
public class TagsEntity {
    private int id;
    private TagsType tag;
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
    public TagsType getTag() {
        return tag;
    }

    public void setTag(TagsType tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagsEntity that = (TagsEntity) o;
        return id == that.id &&
                tag == that.tag &&
                Objects.equals(daysById, that.daysById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, daysById);
    }

    @OneToMany(mappedBy = "tagsByTag")
    public Collection<DaysEntity> getDaysById() {
        return daysById;
    }

    public void setDaysById(Collection<DaysEntity> daysById) {
        this.daysById = daysById;
    }
}
