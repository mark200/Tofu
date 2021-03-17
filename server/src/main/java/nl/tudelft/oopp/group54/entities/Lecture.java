package nl.tudelft.oopp.group54.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lecture_nickname", columnDefinition = "VARCHAR(30) NOT NULL")
    @NotNull
    private String lectureName;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE NOT NULL")
    @NotNull
    private Date startTime;

    @Column(name = "student_join_id", columnDefinition = "VARCHAR(32) NOT NULL")
    @NotNull
    private String studentJoinId;

    @Column(name = "moderator_join_id", columnDefinition = "VARCHAR(32) NOT NULL")
    @NotNull
    private String moderatorJoinId;

    @Column(name = "lecturer_join_id", columnDefinition = "VARCHAR(32) NOT NULL")
    @NotNull
    private String lecturerJoinId;

    public Lecture(){

    }

    /**
     * Constructor for Lecture Entity
     * @param id - Integer
     * @param lectureName - String
     * @param startTime - Date
     * @param studentJoinId - Integer
     * @param moderatorJoinId - Integer
     * @param lecturerJoinId - Integer
     */
    public Lecture(Integer id, @NotNull String lectureName, @NotNull Date startTime, @NotNull String studentJoinId, @NotNull String moderatorJoinId, @NotNull String lecturerJoinId) {
        this.id = id;
        this.lectureName = lectureName;
        this.startTime = startTime;
        this.studentJoinId = studentJoinId;
        this.moderatorJoinId = moderatorJoinId;
        this.lecturerJoinId = lecturerJoinId;
    }

    public Integer getId() {
        return id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getStudentJoinId() {
        return studentJoinId;
    }

    public String getModeratorJoinId() {
        return moderatorJoinId;
    }

    public String getLecturerJoinId() {
        return lecturerJoinId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStudentJoinId(String studentJoinId) {
        this.studentJoinId = studentJoinId;
    }

    public void setModeratorJoinId(String moderatorJoinId) {
        this.moderatorJoinId = moderatorJoinId;
    }

    public void setLecturerJoinId(String lecturerJoinId) {
        this.lecturerJoinId = lecturerJoinId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return getId().equals(lecture.getId()) &&
                getLectureName().equals(lecture.getLectureName()) &&
                getStartTime().equals(lecture.getStartTime()) &&
                getStudentJoinId().equals(lecture.getStudentJoinId()) &&
                getModeratorJoinId().equals(lecture.getModeratorJoinId()) &&
                getLecturerJoinId().equals(lecture.getLecturerJoinId());
    }

    public boolean equalsPrimaryKeys(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return getId().equals(lecture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLectureName(), getStartTime(), getStudentJoinId(), getModeratorJoinId(), getLecturerJoinId());
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", lectureName='" + lectureName + '\'' +
                ", startTime=" + startTime +
                ", studentJoinId=" + studentJoinId +
                ", moderatorJoinId=" + moderatorJoinId +
                ", lecturerJoinId=" + lecturerJoinId +
                '}';
    }
}