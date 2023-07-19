package shop.mtcoding.mallpractice3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mallpractice3.model.Product;
import shop.mtcoding.mallpractice3.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        // 탬플릿 엔진
        // 리퀘스트에 일단 담아두고 home.jsp에 가서
        request.setAttribute("productList", productList);
        productRepository.findAll();

        // 홈이라는 파일안에 있는 모든 내용을 읽어서 리턴해주는 거. 메서드 호출은 안됨.
        // 레스트 컨트롤러면 데이터를 리턴 -> jsp 내용 긁어 붙이는 방식 + 중간에 puoductList 넣기. 불편 그자체
        return "home";

    }

    @GetMapping("/write")
    public String hello() {
        return "write";
    }

    @PostMapping("/product") // 모델명을 적음.
    public String write2(String name, int price, int qty, HttpServletResponse response) {
        System.out.println("name : " + name);
        System.out.println("price : " + price);
        System.out.println("qty : " + qty);

        productRepository.save(name, price, qty);
        return "redirect:/";

//    public void write2(String name, int price, int qty, HttpServletResponse response) {
//        productRepository.save(name, price, qty);
//        response.sendRedirect
    }
}

