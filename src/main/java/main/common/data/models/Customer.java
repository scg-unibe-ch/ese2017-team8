package main.common.data.models;

import javax.persistence.*;

/**
 * This class is the entity for a customer.
 * The entity consists of the casual information about a customer.
 * Those fields are private and can be accessed by invoking the getter and setter methods.
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
//    private String domicile;
    private String phoneNumber;
    private String emailAddress;
    
    
    protected Customer() {}

    public Customer(String firstName, String lastName, String streetName, String streetNumber, String postalCode, String phoneNumber, String emailAddress) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
//        this.domicile = domicile;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

	public void setId(long id) 
	{
		this.id=id;
		
	}

}