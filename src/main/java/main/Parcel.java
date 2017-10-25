package main;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;


/**
 * This class is an entity for parcels.
 * It contains its dimensions, weight and important notes.
 * It is possible to access the properties of a parcel by invoking the getter and setter methods.
 *
 * @author Team8
 * @version 1.0
 */
@Entity
@Table(name = "parcels")
public class Parcel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double length;
	private double width;
	private double height;
	private double weight;
	private boolean dangerous; //i.e. flammable
	private boolean fragile;
	private String comment;

	public Parcel() {}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
