package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Product;
import project.oobat.Model.Stock;
import project.oobat.Model.Supplier;

import project.oobat.Repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    public void saveStock(Stock stock) {
        stock.setDateAdded(java.time.LocalDate.now().toString());
        stockRepository.save(stock);
        // update product quantity
        Product product = stock.getProduct();
        product.setQuantity(stock.getProduct().getQuantity() + stock.getQuantity());
        productService.updateProduct(product);
    }

    public Iterable<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStockbyID(Long id) {
        return stockRepository.findById(id).get();
    }

    public void deleteStock(Stock stock) {
        Product product = stock.getProduct();

        if (stock.getQuantity() > product.getQuantity()) {
            product.setQuantity(0);
            productService.updateProduct(product);
        } else {
            // update product quantity
            product.setQuantity(stock.getProduct().getQuantity() - stock.getQuantity());
            productService.updateProduct(product);
        }
        stockRepository.delete(stock);
    }

    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }
}
