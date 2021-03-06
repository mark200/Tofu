package nl.tudelft.oopp.group54.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class PollKey implements Serializable, Comparable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT AUTO_INCREMENT")
    private Integer id;

    @Column(name = "lecture_id", nullable = false)
    private Integer lectureId;

    public PollKey(Integer id, Integer lectureId) {
        this.id = id;
        this.lectureId = lectureId;
    }

    public PollKey() {

    }

    public int getId() {
        return id;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    //TODO: implement this compare correctly.
    @Override
    public int compareTo(Object o) {
        PollKey that = (PollKey) o;
        return Integer.valueOf(id).compareTo(Integer.valueOf(that.getId()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PollKey that = (PollKey) o;
        return getId() == that.getId()
                && getLectureId() == that.getLectureId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLectureId());
    }
}