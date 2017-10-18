package main;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "product")
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
        return String.format("Order[id='%d', product='%s', quantity='%d', price='%e']",
                id, product,quantity,price);
    }

    @ModelAttribute("order")
    public Order getOrder(){
        return new Order();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){ return price; }

    public Date getPurchaseOrderDate(){
        return purchaseOrderDate;
    }

    public Long getCustomerId(){
        return customerId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}