package main;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is an entity for parcels.
 * It contains its dimensions, weight and important notes.
 * It is possible to access the properties of a parcel by invoking the getter and setter methods.
 *
 * @author Team8
 * @version 1.0
 */
@Entity
@Table(name = "parcel_stats")
public class ParcelStat {

    /**
     * für die Ausgabe aller Änderungen
     */
    private static List instances = new ArrayList();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long parcelId;
    private Long deliveryId;
    private LocalDate changeTime;
    private Delivery.Status newStatus;
    private String user;

    public ParcelStat() {
    }

    public ParcelStat(Long parcelId, Long deliveryId, Delivery.Status newStatus, String user){
        this.parcelId = parcelId;
        this.deliveryId = deliveryId;
        this.newStatus = newStatus;
        this.changeTime = LocalDate.now();
        this.user = user;
        instances.add(this);
        System.out.println(this);
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

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
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

    public LocalDate getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDate changeTime) {
        this.changeTime = changeTime;
    }

    public void printAll(){
        System.out.println(Arrays.toString(instances.toArray()));
    }
}
