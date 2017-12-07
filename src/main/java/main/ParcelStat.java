package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * This class is an entity for parcels.
 * It contains its dimensions, weight and important notes.
 * It is possible to access the properties of a parcel by invoking the getter and setter methods.
 *
 * @author Team8
 * @version 1.0
 */
@Entity
@Table(name = "parcelStats")
public class ParcelStat {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long parcelId;
    private Delivery.Status newStatus;
    private String user;
    private LocalDateTime changeTime;
    private String driver;

    public ParcelStat() {
    }

    public ParcelStat(Long parcelId, Delivery.Status newStatus, String user, String driver){
        this.parcelId = parcelId;
        this.newStatus = newStatus;
        this.changeTime = now();
        this.user = user;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return String.format("user %s changes parcel %d status to %s", user, parcelId, newStatus);
    }

    @ModelAttribute("parcelStat")
    public ParcelStat getParcelStat(){
        return new ParcelStat();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Delivery.Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Delivery.Status newStatus) {
        this.newStatus = newStatus;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
