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
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/manage")
    public String manageProduct(Model model) {
        Product product = new Product();
        Iterable<Product> products = productService.getActiveProducts();
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "admin/manageproduct";
    }

    @GetMapping("/analysis")
    @ResponseBody
    public Map<String, Object> getAnalysis() {
        
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        Iterable<Product> products = productService.getAllProducts();

        int lowestYear = currentYear;
        for (Product product : products) {
            int tempYear = Integer.parseInt(product.getDateAdded().substring(0, 4));
            if (tempYear < lowestYear) {
                lowestYear = tempYear;
            }
        }

        // Generate data for the chart
        List<Integer> totalData = new ArrayList<>();
        List<Integer> activeData = new ArrayList<>();
        List<Integer> deletedData = new ArrayList<>();
        List<Integer> addedData = new ArrayList<>();
        List<String> xaxisCategories = new ArrayList<>();
        int totalCummulative = 0;
        int active = 0;
        for (int year = lowestYear; year <= currentYear; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == currentYear && month > currentMonth + 1) {
                    break;
                }
                int deleted = 0;
                int added = 0;

                for (Product product : products) {
                    
                    int tempYear = Integer.parseInt(product.getDateAdded().substring(0, 4));
                    int tempMonth = Integer.parseInt(product.getDateAdded().substring(5, 7));

                    int tempYearDeleted, tempMonthDeleted;

                    if (product.getDateDeleted() == null) {
                        tempYearDeleted = 0;
                        tempMonthDeleted = 0;
                    }
                    else {
                        tempYearDeleted = Integer.parseInt(product.getDateDeleted().substring(0, 4));
                        tempMonthDeleted = Integer.parseInt(product.getDateDeleted().substring(5, 7));
                    }
                    

                    if (tempYear == year && tempMonth == month) {
                        totalCummulative++;
                        added++;
                        active++;
                    }

                    if (tempYearDeleted == year && tempMonthDeleted == month) {
                        deleted++;
                        active--;
                    }
                    
                }



                totalData.add(totalCummulative);
                activeData.add(active);
                deletedData.add(deleted);
                addedData.add(added);
                xaxisCategories.add(new DateFormatSymbols().getMonths()[month-1] + "/" + year);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("totalData", totalData);
        data.put("activeData", activeData);
        data.put("deletedData", deletedData);
        data.put("addedData", addedData);
        data.put("xaxisCategories", xaxisCategories);

        return data;
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/product/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        productService.deleteProduct(product);
        return "redirect:/admin/product/manage";
    }
}
