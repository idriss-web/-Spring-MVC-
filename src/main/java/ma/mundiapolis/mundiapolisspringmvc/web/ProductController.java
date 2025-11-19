package ma.mundiapolis.mundiapolisspringmvc.web;

import ma.mundiapolis.mundiapolisspringmvc.entities.Product;
import ma.mundiapolis.mundiapolisspringmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/user/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("/admin//delete")
    public String delete(@RequestParam("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/admin/newProduct";
    }
}
