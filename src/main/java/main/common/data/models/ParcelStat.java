package main.common.data.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long userId;
    private LocalDateTime changeTime;
    private Long driverId;

    public ParcelStat() {
    }

    public ParcelStat(Long parcelId, Delivery.Status newStatus, Long userId, Long driverId) {
        this.parcelId = parcelId;
        this.newStatus = newStatus;
        this.changeTime = now();
        this.userId = userId;
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return String.format("user %s changes parcel %d status to %s", userId, parcelId, newStatus);
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }
}
