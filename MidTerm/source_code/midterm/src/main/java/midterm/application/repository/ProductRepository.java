package midterm.application.repository;

import midterm.application.entity.Category;
import midterm.application.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
    Product findProductById(Long id);
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> findProductsByNameContainingIgnoreCase(String name);
    List<Product> findProductsByPriceBetweenAndBrandContainingAndNameContainingOrderByPriceDesc(Integer price1,Integer price2,String brand,String name);
    List<Product> findProductsByPriceBetweenAndBrandContainingAndNameContainingOrderByPriceAsc(Integer price1,Integer price2,String brand,String name);
    List<Product> findProductsByPriceBetweenAndCategoryAndBrandContainingAndNameContainingOrderByPriceDesc(Integer price1, Integer price2, Category category, String brand, String name);
    List<Product> findProductsByPriceBetweenAndCategoryAndBrandContainingAndNameContainingOrderByPriceAsc(Integer price1, Integer price2, Category category, String brand, String name);
}
