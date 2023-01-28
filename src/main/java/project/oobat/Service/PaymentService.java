package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Model.Payment.Status;
import project.oobat.Repository.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getUnpaidPayment(String username){
        return paymentRepository.findByUsernameAndStatus(username, Payment.Status.UNPAID);
    }

    public Boolean isUnpaidPayment(String username){
        return paymentRepository.existsByUsernameAndStatus(username, Payment.Status.UNPAID);
    }

    public void newPayment(Order order){
        Payment payment = order.getPayment();
        double payAmount = 0;
        // loop order product list
        for (Product p: order.getProducts().keySet()){
            payAmount = payAmount + (p.getPrice() * order.getProducts().get(p));
        }
        payment.setAmount(payAmount);
        // payment.setOrder(order);
        // orderRepository.save(order);
        payment.setOrder(order);
        paymentRepository.save(payment);
    }

    public void updateAmount(Order order){
        Payment payment = order.getPayment();
        double payAmount = 0;
        // loop order product list
        for (Product p : order.getProducts().keySet()){
            payAmount = payAmount + (p.getPrice() * order.getProducts().get(p));
        }
        payment.setAmount(payAmount);
        paymentRepository.save(payment);
    }

    public Payment confirmPayment(Payment payment, String username){
        Payment paymentToConfirm = getUnpaidPayment(username);
        paymentToConfirm.setMethod(payment.getMethod());
        String today_date = java.time.LocalDate.now().toString();
        String current_time = java.time.LocalTime.now().toString();
        paymentToConfirm.setDateAdded(today_date);
        paymentToConfirm.setTime(current_time);
        paymentToConfirm.setStatus(Status.COMPLETED);
        savePayment(paymentToConfirm);
        paymentToConfirm.getOrder().setStatus(Order.Status.COMPLETED);
        paymentToConfirm.getOrder().setDate(today_date);
        return paymentToConfirm;
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }

    public Iterable<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

}
