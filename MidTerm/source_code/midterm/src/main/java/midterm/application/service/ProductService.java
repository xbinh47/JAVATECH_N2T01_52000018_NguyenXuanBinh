package midterm.application.service;

import midterm.application.entity.Category;
import midterm.application.entity.Product;
import org.aspectj.weaver.IEclipseSourceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ProductService{
    public List<Product> getAllProducts();
    public Product getProduct(Long id);
    public void deleteProduct(Long id);
    public Product save(Product product);
    public List<Product> getProductsByCategory(Long category_id);
    public List<Product> getProductsByName(String name);
    public List<Product> filterProducts(Map<String,String> params);
    public List<Category> getAllCategories();
}
