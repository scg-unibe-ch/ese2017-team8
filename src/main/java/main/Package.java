package main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Package 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int packageId;
	
	private int length, width, height;
	private float weight;
	private boolean dangerous; //i.e flammable
	private boolean fragile;
	private String comment;
	
	public Package(int id,int length,int width,int height,float weight,boolean dangerous ,boolean fragile,String comment)
	{
		this.packageId=id;
		this.length=length;
		this.width=width;
		this.height=height;
		this.dangerous=dangerous;
		this.fragile=fragile;
		this.comment=comment;
	}
	
	
	@Override
	public String toString()
	{
		String tmp="Package id=" + packageId + "";
		return tmp;
	}
	
}
