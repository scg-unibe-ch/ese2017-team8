package main.driver.presentation.viewmodels;

import main.common.data.models.Delivery;

import java.io.Serializable;

public class DriverDeliveryListModel implements Serializable {
	private Long deliveryId;
	private Long parcelId;
	private double length;
	private double width;
	private double height;
	private double weight;
	private boolean dangerous; //i.e. flammable
	private boolean fragile;
	private String comment;
	private String zeitfenster;
	private String city;
	private String plz;
	private String address;
	private String recipient;
	private Delivery.Status status;
	private int sequence;

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

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isDangerous() {
		return dangerous;
	}

	public void setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getZeitfenster(){return zeitfenster;}

	public void setZeitfenster(String zeitfenster){this.zeitfenster = zeitfenster;}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Delivery.Status getStatus() {
		return status;
	}

	public void setStatus(Delivery.Status status) {
		this.status = status;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}