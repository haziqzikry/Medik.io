package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.PaymentService;

@Controller
@RequestMapping("/user/payment")
public class UserPaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/checkout")
    public String checkout() {
        return "user/payment";
    }
}
