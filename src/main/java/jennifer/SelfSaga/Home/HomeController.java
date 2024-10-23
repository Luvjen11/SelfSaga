package jennifer.SelfSaga.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/selfsaga/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("selfsaga/home")
    public String handleWelcome() {
        return "home"; // Return home page content
    }

    @GetMapping("/selfsaga")
    public String handleSelfsaga() {
        return "selfsaga"; 
    }
}
