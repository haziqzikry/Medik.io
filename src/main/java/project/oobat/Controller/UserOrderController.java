package project.oobat.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.AppUser;
import project.oobat.Model.Product;
import project.oobat.Service.OrderService;
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user/order")
public class UserOrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/view")
    public String viewOrder() {
        return "user/vieworder";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("cart", orderService.getCartByUsername(principal.getName()));
        // System.out.println(principal.getName());
        return "user/cart";
    }

    @GetMapping("/remove/cart/{product}")
    public String removeFromCart(@PathVariable("product") Long productId, Principal principal, Model model) {
        Product product = productService.getProductById(productId);
        orderService.removeProductFromCart(product, principal.getName());
        return "redirect:/user/order/cart";
    }

}
