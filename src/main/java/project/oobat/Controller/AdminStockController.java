package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.Product;
import project.oobat.Model.Stock;
import project.oobat.Model.Supplier;
import project.oobat.Service.ProductService;
import project.oobat.Service.StockService;
import project.oobat.Service.SupplierService;
import project.oobat.Repository.StockRepository;

@Controller
@RequestMapping("/admin/stock")
public class AdminStockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @GetMapping("/manage")
    public String manageStock(Model model) {
        Stock stock = new Stock();
        model.addAttribute("stock", stock);
        Iterable<Stock> stocks = stockService.getAllStock();
        model.addAttribute("stocks", stocks);
        Iterable<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        Iterable<Product> products = productService.getActiveProducts();
        model.addAttribute("products", products);

        return "admin/managestock";
    }

    @PostMapping("/add")
    public String addStock(Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/admin/stock/manage";
    }

    @GetMapping("/delete/{id}")
    public String removefromStock(@PathVariable("id") Long stockId) {
        Stock stock = stockService.getStockbyID(stockId);
        stockService.deleteStock(stock);
        return "redirect:/admin/stock/manage";
    }

    @GetMapping("/update/{id}")
    public String updateStock(@PathVariable("id") Long stockId, Stock stock, Supplier supplier, Product product,
            Model model) {
        Stock stockUpdate = stockService.getStockbyID(stockId);
        model.addAttribute("stock", stockUpdate);
        Iterable<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        Iterable<Product> products = productService.getActiveProducts();
        model.addAttribute("products", products);

        return "admin/updatestock";

    }
}
