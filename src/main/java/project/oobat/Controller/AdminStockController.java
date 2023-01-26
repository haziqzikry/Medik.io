package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.Stock;
import project.oobat.Service.StockService;
import project.oobat.Repository.StockRepository;

@Controller
@RequestMapping("/admin/stock")
public class AdminStockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/manage")
    public String manageStock(Model model) {
        Stock stock = new Stock();
        model.addAttribute("stock", stock);
        Iterable<Stock> stocks = stockService.getAllStock();
        model.addAttribute("stocks", stocks);
        return "admin/managestock";
    }

    @PostMapping("/add")
    public String addStock(Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/admin/stock/manage";
    }

    @GetMapping("/delete/{id}")
    public String removeFromCart(@PathVariable("id") Long stockId) {
        Stock stock = stockService.getStockbyID(stockId);
        stockService.deleteStock(stock);
        return "redirect:/admin/stock/manage";
    }
}
