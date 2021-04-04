package pl.kinga.produkty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) ProductCategory category) {
        return "home";
    }

    @GetMapping("/allProducts")
    public String product(@RequestParam(required = false, value = "category") ProductCategory category, Model model) {
        List<Product> products = productRepository.findAll();

        if (category != null) {
            model.addAttribute("product", new Product());
            model.addAttribute("products", productRepository.findByCategory(category));
        } else {
            model.addAttribute("products", productRepository.findAll());
        }
        double sum = 0;
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                sum += product.getPrice();
                model.addAttribute("priceSum", sum);
            }
        }
        return "allProducts";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam int price, @RequestParam ProductCategory category) {
        Product product = new Product(name, price, category);
        productRepository.addProduct(product);
        return "redirect:/allProducts";
    }
}


