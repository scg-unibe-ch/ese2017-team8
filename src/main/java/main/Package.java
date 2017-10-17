package main;

import javax.persistence.*;

@Entity
@Table(name = "packages")
public class Package
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int length, width, height;
	private float weight;
	private boolean dangerous; //i.e flammable
	private boolean fragile;
	private String comment;

	protected Package() {};

	public Package(int length,int width,int height,float weight,boolean dangerous ,boolean fragile,String comment)
	{
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
		String tmp="Package id=" + id + "";
		return tmp;
	}

}
