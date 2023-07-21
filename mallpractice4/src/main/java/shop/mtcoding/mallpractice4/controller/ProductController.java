package shop.mtcoding.mallpractice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mallpractice4.model.Product;
import shop.mtcoding.mallpractice4.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);
        return "home";
    }

    @PostMapping("/product")
    public void write(String name, int price, int qty, HttpServletResponse response) throws IOException {
        productRepository.save(name, price, qty);
        response.sendRedirect("/");
    }

    @GetMapping("/write")
    public String writePage(){
        return "write";
    }

    @PostMapping("/product/update")
    public String update(int id, String name, int price, int qty, HttpServletRequest request) {
        productRepository.updateById(id, name, price, qty);

        return "redirect:/";
    }
    @PostMapping("/product/delete")
    public String delete(int id, HttpServletRequest request) {
        productRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        Product product = productRepository.findById(id);
        request.setAttribute("p", product);
       return "detail";
    }
}
