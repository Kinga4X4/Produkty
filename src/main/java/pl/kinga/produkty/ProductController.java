package pl.kinga.produkty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;
    List<Product> products;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/allProducts")
    public String product(Model model) {
        products = productRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "AllProducts";
    }

//    @GetMapping("/category")
//    public String productByCategory(@RequestParam String category, Model model) {
//        products = productRepository.findByCategory(category);
//        if (products != null) {
//            model.addAttribute("products", products);
//            return "AllProducts";
//        } else {
//            return "redirect:/";
//        }
//    }

    @PostMapping("/dodaj")
    public String add(Product product) {
        products.add(product);
        return "redirect:/allProducts";
    }
}
