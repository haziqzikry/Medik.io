package project.oobat.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.AppUser;
import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Service.OrderService;
import project.oobat.Service.PaymentService;
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/view")
    public String viewOrder(Model model, Principal principal) {
        Order order = new Order();
        model.addAttribute("order", order);
        Iterable<Order> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);
        Iterable<Payment> payments = paymentService.getAllPayment();
        model.addAttribute("payments", payments);

        return "user/vieworder";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        Order cart = orderService.getCartByUsername(principal.getName());
        model.addAttribute("cart", cart);
        // System.out.println(principal.getName());
        System.out.println(cart.getProducts().size());
        return "user/cart";
    }

    @GetMapping("/remove/cart/{product}")
    public String removeFromCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        Product product = productService.getProductById(productId);
        orderService.removeProductFromCart(product, principal.getName());
        return "redirect:/user/order/cart";
    }

    @GetMapping("/add-quantity/cart/{product}")
    public String addQuantityToCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        // increase the quantity of the selected product in the cart
        Order cart = orderService.getCartByUsername(principal.getName());
        Product product = productService.getProductById(productId);
        // products in cart is a map
        cart.getProducts().put(product, cart.getProducts().get(product) + 1);
        orderService.saveOrder(cart);
        return "redirect:/user/order/cart";
    }

    @GetMapping("/remove-quantity/cart/{product}")
    public String removeQuantityToCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        // decrease the quantity of the selected product in the cart
        Order cart = orderService.getCartByUsername(principal.getName());
        Product product = productService.getProductById(productId);
        // products in cart is a map
        cart.getProducts().put(product, cart.getProducts().get(product) - 1);
        orderService.saveOrder(cart);
        return "redirect:/user/order/cart";
    }

}
