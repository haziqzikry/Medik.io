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

    @Autowired
    private ProductService productService;

    // ORDER CRUD

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order saveOrderAndFlush(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    // CART METHODS

    public void newCart(Order order) {
        order.setStatus(Order.Status.CART);
        Order newOrder = orderRepository.saveAndFlush(order);
        paymentService.newPayment(newOrder);
    }

    public void addProductToCart(Long productId, String user) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        cart.getProducts().put(product, 1);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void removeProductFromCart(Long productId, String user) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        cart.getProducts().remove(product);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void updateProductQuantityInCart(Long productId, String user, int quantity) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        cart.getProducts().put(product, cart.getProducts().get(product) + quantity);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void placeOrder(Order order) {
        order.setStatus(Order.Status.PENDING);
        orderRepository.save(order);
    } 
    
    public Order getCartByUserId(Long id) {
        return orderRepository.findByUserIdAndStatus(id, Order.Status.CART);
    }

    public Order getCartByUsername(String username) {
        return orderRepository.findByUsernameAndStatus(username, Order.Status.CART);
    }
    

}
