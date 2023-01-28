package project.oobat.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.AppUser;
import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Model.Order.Status;
import project.oobat.Repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AppUserService userService;

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

    public Iterable<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    // CART METHODS

    public void newCart(Order order) {
        order.setStatus(Order.Status.CART);
        Order newOrder = orderRepository.saveAndFlush(order);
        paymentService.newPayment(newOrder);
    }

    public void clearCart(String user) {
        Order cart = getCartByUsername(user);
        for(Product p : cart.getProducts().keySet()) {
            p.setQuantity(p.getQuantity() + cart.getProducts().get(p));
            productService.saveProduct(p);
        }
        cart.getProducts().clear();
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void addProductToCart(Long productId, String user) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        cart.getProducts().put(product, 1);
        product.setQuantity(product.getQuantity() - 1);
        productService.saveProduct(product);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void removeProductFromCart(Long productId, String user) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        int quantity = cart.getProducts().get(product);
        cart.getProducts().remove(product);
        product.setQuantity(product.getQuantity() + quantity);
        productService.saveProduct(product);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void updateProductQuantityInCart(Long productId, String user, int quantity) {
        Product product = productService.getProductById(productId);
        Order cart = getCartByUsername(user);
        cart.getProducts().put(product, cart.getProducts().get(product) + quantity);
        if(cart.getProducts().get(product) == 0) {
            cart.getProducts().remove(product);
        }
        if(product.getQuantity() - quantity < 0) {
            return;
        }
        product.setQuantity(product.getQuantity() - quantity);
        productService.saveProduct(product);
        Order newCart = orderRepository.saveAndFlush(cart);
        paymentService.updateAmount(newCart);
    }

    public void placeOrder(Order order) {
        order.setStatus(Order.Status.PENDING);
        order.setDate(java.time.LocalDate.now().toString());
        Payment payment = order.getPayment();
        payment.setStatus(Payment.Status.UNPAID);
        paymentService.savePayment(payment);
        orderRepository.save(order);
    }

    public Order getCartByUserId(Long id) {
        return orderRepository.findByUserIdAndStatus(id, Order.Status.CART);
    }

    public Order getCartByUsername(String username) {
        Order cart = orderRepository.findByUsernameAndStatus(username, Order.Status.CART);
        if(cart == null) {
            AppUser user = userService.loadUserByUsername(username);
            Order newCart = new Order(new Payment(), user, Status.CART);
            newCart(newCart);
            cart = newCart;
        }
        // sort the products in the cart by name
        Order sortedCart = sortProducts(cart);
        return sortedCart;
    }
    
    // public Order getCartByUsername(String username) {
    //     Order cart = orderRepository.findByUsernameAndStatus(username, Order.Status.CART);
    
    //     List<Map.Entry<Product, Integer>> productsList = new ArrayList<>(cart.getProducts().entrySet());
    //     Collections.sort(productsList, new Comparator<Map.Entry<Product, Integer>>() {
    //         @Override
    //         public int compare(Map.Entry<Product, Integer> o1, Map.Entry<Product, Integer> o2) {
    //             return o1.getKey().getName().compareTo(o2.getKey().getName());
    //         }
    //     });
    
    //     Map<Product, Integer> sortedProducts = new LinkedHashMap<>();
    //     for (Map.Entry<Product, Integer> entry : productsList) {
    //         sortedProducts.put(entry.getKey(), entry.getValue());
    //     }
    //     cart.setProducts(sortedProducts);
    //     return cart;
    // }

    public Order sortProducts(Order order) {
        List<Map.Entry<Product, Integer>> productsList = new ArrayList<>(order.getProducts().entrySet());
        Collections.sort(productsList, new Comparator<Map.Entry<Product, Integer>>() {
            @Override
            public int compare(Map.Entry<Product, Integer> o1, Map.Entry<Product, Integer> o2) {
                return o1.getKey().getName().compareTo(o2.getKey().getName());
            }
        });
    
        Map<Product, Integer> sortedProducts = new LinkedHashMap<>();
        for (Map.Entry<Product, Integer> entry : productsList) {
            sortedProducts.put(entry.getKey(), entry.getValue());
        }
        order.setProducts(sortedProducts);
        return order;
    }

}
