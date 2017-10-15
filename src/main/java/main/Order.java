package main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String product;
    private int quantity;
    private double price;
    private Date purchaseOrderDate;
    private int customerID;
   
    
    protected Order() {}

    public Order(String product, int quantity, double price, Date purchaseOrderDate, int customer) {
      this.product = product;
      this.quantity = quantity;
      this.price = price;
      this.purchaseOrderDate = purchaseOrderDate;
      this.customerID = customer;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%d, product='%s', price='%s', customerId=%d]",
                id, product, customerID);
    }

}