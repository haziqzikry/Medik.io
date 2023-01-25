package project.oobat.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.AppUser;
import project.oobat.Model.Order;
import project.oobat.Model.Payment;
import project.oobat.Model.Product;
import project.oobat.Model.Stock;
import project.oobat.Model.Supplier;
import project.oobat.Model.AppUser.Role;
import project.oobat.Model.Order.Status;
import project.oobat.Service.AppUserService;
import project.oobat.Service.OrderService;
import project.oobat.Service.ProductService;
import project.oobat.Service.StockService;
import project.oobat.Service.SupplierService;
import project.oobat.Model.AppUser.Role;

@Controller
@RequestMapping("/")
public class BuiltInDataController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @Autowired
    private SupplierService supplierService;
    
    @GetMapping("/data")
    public String data() {
        return "misc/data";
    }

    @PostMapping("/data")
    public String dataSubmit() {
        // CREATE USERS
        AppUser user1 = new AppUser("Haziq Zikry", "qiqi@mail", "qiqi", "1", "No. 2, Jalan Baru", "0145892141", Role.CUSTOMER);
        AppUser user2 = new AppUser("Fara Lan", "fara@mail", "fara", "1", "No. 3, Jalan Baru", "0145892142", Role.CUSTOMER);
        AppUser user3 = new AppUser("Naim Jalil", "naim@mail", "naim", "1", "No. 8, Jalan Japun", "0136547521", Role.PHARMACIST);
        appUserService.registerCustomer(user1);
        appUserService.registerCustomer(user2);
        appUserService.registerPharmacist(user3);

        // CREATE PRODUCTS
        Product product1 = new Product("Panadol", "For headache", 5.00, 0);
        Product product2 = new Product("Paracetamol", "For fever", 3.00, 0);
        Product product3 = new Product("Vitamin C", "For cold", 2.00, 0);
        Product product4 = new Product("Vitamin D", "For bone", 2.00, 0);
        Product product5 = new Product("Vitamin E", "For skin", 2.00, 0);
        Product product6 = new Product("Vitamin B", "For brain", 2.00, 0);
        Product product7 = new Product("Vitamin A", "For eyes", 2.00, 0);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);
        productService.saveProduct(product7);

        //CREATE SUPPLIERS
        Supplier supplier1 = new Supplier("Kamal Supply", "No. 2, Jalan Jalan", "0145892141", "supplier1@mail");
        Supplier supplier2 = new Supplier("Ahmad Supply", "No. 8, Jalan Jalan", "0136547521", "supplier2@mail");
        supplierService.saveSupplier(supplier1);
        supplierService.saveSupplier(supplier2);

        // CREATE STOCKS
        Stock stock1 = new Stock(product2, supplier1, user2, 50);
        Stock stock2 = new Stock(product4, supplier1, user2, 50);
        Stock stock3 = new Stock(product7, supplier2, user2, 50);
        stockService.saveStock(stock1);
        stockService.saveStock(stock2);
        stockService.saveStock(stock3);

        // CREATE ORDERS
        // list of products
        // List<Product> items1 = new ArrayList<>();
        // items1.add(product6);
        // items1.add(product7);
        List<Product> items2 = new ArrayList<>(Arrays.asList(product1, product2, product4));
        List<Product> items3 = new ArrayList<>(Arrays.asList(product2, product6));
        // HashMap<Product, Integer> items4 = new HashMap<>();
        // items4.put(product2, 1);
        // items4.put(product4, 3);
        Order order1 = new Order(new Payment(), user1, items2, Status.CART);
        orderService.newCart(order1);

        
        


        System.out.println("data thru post");
        return "misc/data";
    }

}
