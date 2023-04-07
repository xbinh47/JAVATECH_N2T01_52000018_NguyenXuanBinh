package midterm.application.serviceimpl;

import midterm.application.entity.Category;
import midterm.application.entity.Product;
import midterm.application.repository.CategoryRepository;
import midterm.application.repository.ProductRepository;
import midterm.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Long category_id) {
        return productRepository.getProductsByCategory(category_id);
    }
    public List<Product> getProductsByName(String name){
        if(name == null){
            return productRepository.findAll();
        }
        return productRepository.findProductsByNameContainingIgnoreCase(name);
    };

    public List<Product> filterProducts(Map<String,String> params){
        Integer category_id = 0;
        Integer price1 = 0;
        Integer price2 = 999999;
        Integer sort_type = 1;
        String brand = "";
        String name = "";

        if(params.get("category_id") != null){
            category_id = Integer.parseInt(params.get("category_id"));
        }
        if(params.get("price1") != null){
            price1 = Integer.parseInt(params.get("price1"));
        }
        if(params.get("price2") != null){
            price2 = Integer.parseInt(params.get("price2"));
        }
        if(params.get("sort_type") != null) {
            sort_type = Integer.parseInt(params.get("sort_type"));
        }
        if(params.get("brand") != null){
            brand = params.get("brand");
        }
        if(params.get("name") != null){
            name = params.get("name");
        }
        if(category_id != 0){
            Category category = categoryRepository.findById((long) category_id).get();
            if(!price1.equals(null) && !price2.equals(null)){
                if(sort_type == 0)
                    return productRepository.findProductsByPriceBetweenAndCategoryAndBrandContainingAndNameContainingOrderByPriceDesc(price1,price2,category,brand,name);
                else
                    return productRepository.findProductsByPriceBetweenAndCategoryAndBrandContainingAndNameContainingOrderByPriceAsc(price1,price2, category,brand,name);
            }
            else {
                return productRepository.getProductsByCategory((long) category_id);
            }
        }else {
            if(!price1.equals(null) && !price2.equals(null)){
                if(sort_type == 0)
                    return productRepository.findProductsByPriceBetweenAndBrandContainingAndNameContainingOrderByPriceDesc(price1,price2,brand,name);
                else
                    return productRepository.findProductsByPriceBetweenAndBrandContainingAndNameContainingOrderByPriceAsc(price1,price2,brand,name);
            }
            else {
                return productRepository.findAll();
            }
        }
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
