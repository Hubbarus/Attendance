package com.paul.attendance.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Days", schema = "attendanceDB", catalog = "")
public class DaysEntity {
    private int id;
    private Date date;
    private WorkersEntity workersByWorker;
    private TagsEntity tagsByTag;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaysEntity that = (DaysEntity) o;
        return id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(workersByWorker, that.workersByWorker) &&
                Objects.equals(tagsByTag, that.tagsByTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, workersByWorker, tagsByTag);
    }

    @ManyToOne
    @JoinColumn(name = "worker", referencedColumnName = "id", nullable = false)
    public WorkersEntity getWorkersByWorker() {
        return workersByWorker;
    }

    public void setWorkersByWorker(WorkersEntity workersByWorker) {
        this.workersByWorker = workersByWorker;
    }

    @ManyToOne
    @JoinColumn(name = "tag", referencedColumnName = "id", nullable = false)
    public TagsEntity getTagsByTag() {
        return tagsByTag;
    }

    public void setTagsByTag(TagsEntity tagsByTag) {
        this.tagsByTag = tagsByTag;
    }
}