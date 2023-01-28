package project.oobat.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public static enum Status {
        CART("CART"),
        PENDING("PENDING"),
        COMPLETED("COMPLETED"),
        CANCELLED("CANCELLED");

        private String status;

        private Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

    }

    @Id
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    private Long id;

    // one to one relationship with payment (one order can have one payment)
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    // many to one relationship with user (one user can have many orders)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    // @ManyToMany
    // @JoinTable(
    // name = "order_product",
    // joinColumns = @JoinColumn(name = "order_id"),
    // inverseJoinColumns = @JoinColumn(name = "product_id"))
    // private List<Product> products;

    @ElementCollection
    @CollectionTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    // date column with default value of current date and date format of yyyy-mm-dd
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private String date;

    // constructor without id
    public Order(Payment payment, AppUser user, Map<Product, Integer> items4, Status status) {
        this.payment = payment;
        this.user = user;
        this.products = items4;
        this.status = status;
    }

    // constructor with only user and status
    public Order(Payment payment, AppUser user, Status status) {
        this.payment = payment;
        this.user = user;
        this.status = status;
    }

    // public void addProduct(Product product, int quantity) {
    // products.put(product, quantity);
    // }

    // public void removeProduct(Product product) {
    // products.remove(product);
    // }

    // public void updateProductQuantity(Product product, int newQuantity) {
    // products.put(product, newQuantity);
    // }

}
