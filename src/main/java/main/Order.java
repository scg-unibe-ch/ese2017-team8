package main;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String product;
    private int quantity;
    private double price;
    private Date purchaseOrderDate;
    private Long customerId;
   
    
    protected Order() {}

    public Order(String product, int quantity, double price, Date purchaseOrderDate, Long customerId) {
      this.product = product;
      this.quantity = quantity;
      this.price = price;
      this.purchaseOrderDate = purchaseOrderDate;
      this.customerId = customerId;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%d, product='%s', price='%s', customerId=%d]",
                id, product, customerId);
    }

}