package pl.kinga.produkty;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("karob", 25, ProductCategory.GROCERIES));
        products.add(new Product("masło ghee", 15, ProductCategory.GROCERIES));
        products.add(new Product("chusteczki", 7, ProductCategory.HOUSEHOLDS));
        products.add(new Product("obraz", 80, ProductCategory.OTHER));
        products.add(new Product("szczotka do butów", 12, ProductCategory.HOUSEHOLDS));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByCategory(ProductCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}


