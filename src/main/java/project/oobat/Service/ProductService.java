package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Product;
import project.oobat.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getActiveProducts() {
        return productRepository.findByActive(true);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void saveProduct(Product product) {
        // set product date added to today
        product.setDateAdded(java.time.LocalDate.now().toString());
        product.setActive(true);
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        product.setActive(false);
        product.setDateDeleted(java.time.LocalDate.now().toString());
        productRepository.save(product);
    }

}
