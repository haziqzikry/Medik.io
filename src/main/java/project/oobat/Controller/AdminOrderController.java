package project.oobat.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.AppUserService;
import project.oobat.Service.OrderService;
import project.oobat.Model.AppUser;
import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Service.OrderService;
import project.oobat.Service.PaymentService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/manage")
    public String manageOrder(Model model, Principal principal) {
        Order order = new Order();
        model.addAttribute("order", order);
        Iterable<Order> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);
        Iterable<Payment> payments = paymentService.getAllPayment();
        model.addAttribute("payments", payments);
        Iterable<AppUser> appusers = appUserService.getAllAppUsers();
        model.addAttribute("appusers", appusers);
        return "admin/manageorder";
    }

}
