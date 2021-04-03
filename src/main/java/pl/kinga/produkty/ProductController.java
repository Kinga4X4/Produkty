package pl.kinga.produkty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String product(@RequestParam(required = false, name = "category") ProductCategory category, Model model) {
        List<Product> products;
        if (category != null) {
            model.addAttribute("product", new Product());
            model.addAttribute("products", productRepository.findByCategory(category));

        } else {
            model.addAttribute("products", productRepository.findAll());
            return "allProducts";
        }
        return "allProducts";
    }

    @PostMapping("/dodaj")
    public String add(Product product) {
        productRepository.products.add(product);
        return "redirect:/allProducts";
    }

}
