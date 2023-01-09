package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Product;
import project.oobat.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        // set product date added to today
        product.setDateAdded(java.time.LocalDate.now().toString());
        product.setQuantity(0);
        productRepository.save(product);
    }

}
