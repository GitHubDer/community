package life.majiang.community.contriller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //加这个的话会自动识别扫描当前的类，把它作为spring的一个bean去管理 同时也识别它为一个Controller,Controller的意思就是允许这个类去接收前端的请求
public class IndexController {

//    @GetMapping("/hello") //相当于访问/templates/index.html
    @GetMapping("/") //一个反斜杠的时候代表返回默认路径，即templates下的index
    public String hello(@RequestParam(name = "name", required=false, defaultValue="World") String name, Model model) { //请求的参数
        model.addAttribute("name", name);
        return "index";
    }

}
