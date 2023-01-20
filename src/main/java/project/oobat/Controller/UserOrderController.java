package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.OrderService;

@Controller
@RequestMapping("/user/order")
public class UserOrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/view")
    public String viewOrder() {
        return "vieworder";
    }

    @GetMapping("/cart")
    public String viewCart() {
        return "cart";
    }
}
