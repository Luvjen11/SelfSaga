package jennifer.SelfSaga.ContentController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class ContentController {

    @GetMapping("/selfsaga/home")
    public String handleWelcome() {
        return "home";  // Return home page
    }
}
