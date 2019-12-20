package life.majiang.community.contriller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //加这个的话会自动识别扫描当前的类，把它作为spring的一个bean去管理 同时也识别它为一个Controller,Controller的意思就是允许这个类去接收前端的请求
public class HelloController {

    @ResponseBody
    @GetMapping("/hello") //相当于访问/templates/hello.html
    public String hello(@RequestParam(name = "name") String name, Model model) { //请求的参数
        model.addAttribute("name", name);
        return "hello";
    }

}
