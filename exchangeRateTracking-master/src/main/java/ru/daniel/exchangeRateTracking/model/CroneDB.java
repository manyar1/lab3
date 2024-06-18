package ru.daniel.exchangeRateTracking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "synchro_crone")
public class CroneDB {

    @Id
    @Column(name = "id")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "start_crone")
    private String startCrone;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "end_crone")
    private String endCrone;

    public CroneDB() {}

    public CroneDB(Integer id, String startDate, String startCrone, String endDate, String endCrone) {
        this.id = id;
        this.startDate = startDate;
        this.startCrone = startCrone;
        this.endDate = endDate;
        this.endCrone = endCrone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartCrone() {
        return startCrone;
    }

    public void setStartCrone(String startCrone) {
        this.startCrone = startCrone;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndCrone() {
        return endCrone;
    }

    public void setEndCrone(String endCrone) {
        this.endCrone = endCrone;
    }

    @Override
    public String toString() {
        return "CroneDB{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", startCrone='" + startCrone + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endCrone='" + endCrone + '\'' +
                '}';
    }
}
