package main;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "parcels")
public class Parcel {

	@Id
	@GeneratedValue
	private long id;  //not Long? TODO

	private double length;
	private double width;
	private double height;
	private double weight;
	private boolean dangerous; //i.e. flammable
	private boolean fragile;
	private String comment;

	public Parcel() {};

	public Parcel(double length, double width, double height, double weight, boolean dangerous, boolean fragile, String comment) {
		this.length=length;
		this.width=width;
		this.height=height;
		this.weight=weight;
		this.dangerous=dangerous;
		this.fragile=fragile;
		this.comment=comment;
	}

	@Override
	public String toString() {
		return String.format("Parcel – Id: %d", id);
	}

	@ModelAttribute("parcel")
	public Parcel getParcel(){
		return new Parcel();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
