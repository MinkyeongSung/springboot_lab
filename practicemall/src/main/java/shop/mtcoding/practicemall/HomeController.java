package shop.mtcoding.practicemall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController // 리턴되는 이름의 파일음 찾아냄
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home"; // ViewResolver 클래스 발동 webapp/WEB-INF/views/home.jsp
    }

    @GetMapping
    public String write(){
        return "write";
    }
}

