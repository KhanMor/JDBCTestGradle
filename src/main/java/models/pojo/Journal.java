package models.pojo;


import java.sql.Timestamp;

/**
 * Created by Mordr on 24.02.2017.
 */
public class Journal {
    private Integer id;
    private Integer lection_id;
    private Integer student_id;
    private Timestamp date;

    public Journal() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLection_id() {
        return lection_id;
    }

    public void setLection_id(Integer lection_id) {
        this.lection_id = lection_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
