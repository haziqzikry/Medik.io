package project.oobat.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    public static enum Status {
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

    public static enum Method {
        CASH("CASH"),
        CARD("CARD"),
        EWALLET("EWALLET");

        private String method;

        private Method(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }

    }

    @Id
    @SequenceGenerator(name = "payments_seq", sequenceName = "payments_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_seq")
    private Long id;

    // one to one relationship with order (one payment can have one order)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated
    private Method method;

    @Enumerated
    private Status status;

    // column for date added with default value of current date
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private String dateAdded;

    @Column
    private String time;

    @Column
    private double amount;

}
