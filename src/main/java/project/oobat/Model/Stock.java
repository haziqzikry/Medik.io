package project.oobat.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @SequenceGenerator(name = "stocks_seq", sequenceName = "stocks_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_seq")
    private Long id;

    // many to one relationship with product (one product can have many stocks)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // many to one relationship with supplier (one supplier can have many stocks)
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // many to one relationship with appuser(pharmacist) (one pharmacist can have
    // many stocks)
    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appuser;

    // column for date added with default value of current date
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private String dateAdded;

    private int quantity;

    // constructor without id
    public Stock(Product product, Supplier supplier, AppUser appuser, int quantity) {
        this.product = product;
        this.supplier = supplier;
        this.appuser = appuser;
        this.quantity = quantity;

    }

}
