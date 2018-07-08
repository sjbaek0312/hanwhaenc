package examples.helloboard.controller;

import examples.helloboard.dto.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/boards")
public class BoardController {
    @GetMapping
    public String list(LoginInfo loginInfo){
        System.out.println(loginInfo);
        return "list";
    }

    @GetMapping(value = "/write")
    public String write(){
        return "write";
    }
}
