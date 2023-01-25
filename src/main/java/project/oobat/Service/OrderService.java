package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    public void saveOrder(Order order) {
        Order newOrder = orderRepository.saveAndFlush(order);
        paymentService.newPayment(newOrder);
    }

    public Order saveOrderAndFlush(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    

}
