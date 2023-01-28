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
import project.oobat.Model.Product;
import project.oobat.Service.OrderService;
import project.oobat.Service.PaymentService;
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user/order")
public class UserOrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/view")
    public String viewOrder() {
        return "user/vieworder";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        Order cart = orderService.getCartByUsername(principal.getName());
        model.addAttribute("cart", cart);
        return "user/cart";
    }

    @GetMapping("/clear/cart")
    public String clearCart(Principal principal) {
        orderService.clearCart(principal.getName());
        return "redirect:/user/order/cart";
    }

    @GetMapping("/remove/cart/{product}")
    public String removeFromCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        orderService.removeProductFromCart(productId, principal.getName());
        return "redirect:/user/order/cart";
    }

    @GetMapping("/add/cart/{product}")
    public String addToCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        orderService.addProductToCart(productId, principal.getName());
        return "redirect:/user/order/cart";
    }

    @GetMapping("/add-quantity/cart/{product}")
    public String addQuantityToCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        orderService.updateProductQuantityInCart(productId, principal.getName(), 1);
        return "redirect:/user/order/cart";
    }

    @GetMapping("/remove-quantity/cart/{product}")
    public String removeQuantityToCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        orderService.updateProductQuantityInCart(productId, principal.getName(), -1);
        return "redirect:/user/order/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Principal principal, Model model) {
        Order cart = orderService.getCartByUsername(principal.getName());
        model.addAttribute("cart", cart);
        return "user/checkout";
    }
    

}
