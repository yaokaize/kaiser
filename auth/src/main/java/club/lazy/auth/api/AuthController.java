package club.lazy.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/sayHi")
    public String sayHi(){
        return "say hi";
    }
}
