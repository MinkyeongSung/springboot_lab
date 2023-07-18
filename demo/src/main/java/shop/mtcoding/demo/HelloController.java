package shop.mtcoding.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.Random;

@RestController
public class HelloController {

    // http://localhost:8080/
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String hello(){
        return "<h1>home<h1><a href=\"./미니프로젝트 1 - 야구 관리 프로그램.pdf\">1. 미니프로젝트 1 - 야구관리 프로그램</a>";
    }

    @GetMapping("/check")
    public void check(){
        System.out.println("/check 호출됨");
    }

    @GetMapping("/random/number")
    public String sdaf(){
        Random r = new Random();
        int num = r.nextInt(5);
        return num+"";
//        Integer num = r.nextInt(5);
//        return num.toString();
    }


}
