package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Product;
import project.oobat.Model.Stock;
import project.oobat.Repository.StockRepository;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductService productService;

    public void saveStock(Stock stock) {
        stockRepository.save(stock);
        // update product quantity
        Product product = stock.getProduct();
        product.setQuantity(stock.getProduct().getQuantity() + stock.getQuantity());
        productService.updateProduct(product);
    }
}
