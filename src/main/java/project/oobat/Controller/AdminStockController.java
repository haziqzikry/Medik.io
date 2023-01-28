package project.oobat.Controller;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/analysis/{id}")
    @ResponseBody
    public Map<String, Object> getAnalysis(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        //get all stock of a product
        Iterable<Stock> stocksOfProduct = stockService.getStockByProduct(product);

        //calculate stock in month of every year and total cummulative
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        int lowestYear = currentYear;
        for (Stock stock : stocksOfProduct) {
            int tempYear = Integer.parseInt(stock.getDateAdded().substring(0, 4));
            if (tempYear < lowestYear) {
                lowestYear = tempYear;
            }
        }

        List<Integer> stockInMonth = new ArrayList<>();
        List<Integer> stockCummulative = new ArrayList<>();
        List<String> xaxisCategory = new ArrayList<>();
        int totalStock = 0;

        for (int year = lowestYear; year <= currentYear; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == currentYear && month > currentMonth + 1) {
                    break;
                }
                int stockInMonthTemp = 0;
                for (Stock stock : stocksOfProduct) {
                    int tempYear = Integer.parseInt(stock.getDateAdded().substring(0, 4));
                    int tempMonth = Integer.parseInt(stock.getDateAdded().substring(5, 7));
                    if (tempYear == year && tempMonth == month) {
                        stockInMonthTemp += stock.getQuantity();
                        totalStock += stock.getQuantity();
                    }
                }
                
                if(totalStock > 0){
                    if(stockCummulative.size() == 0){
                        stockInMonth.add(0);
                        stockCummulative.add(0);

                        if(month == 1){
                            xaxisCategory.add(new DateFormatSymbols().getMonths()[11] + " " + (year-1));
                        }else{
                            xaxisCategory.add(new DateFormatSymbols().getMonths()[month-2] + " " + year);
                        }
                    }

                    stockInMonth.add(stockInMonthTemp);
                    stockCummulative.add(totalStock);
                    xaxisCategory.add(new DateFormatSymbols().getMonths()[month-1] + " " + year);
                }
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("stockInMonth", stockInMonth);
        data.put("stockCummulative", stockCummulative);
        data.put("xaxisCategory", xaxisCategory);

        return data;
        
    }
}
