package life.majiang.community.contriller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //加这个的话会自动识别扫描当前的类，把它作为spring的一个bean去管理 同时也识别它为一个Controller,Controller的意思就是允许这个类去接收前端的请求
public class IndexController {

//    @GetMapping("/hello") //相当于访问/templates/index.html
//    public String hello(@RequestParam(name = "name", required=false, defaultValue="World") String name, Model model) { //请求的参数
//        model.addAttribute("name", name);
//        return "hello";
//    }

    @Autowired
    private QuestionService questionService;

    //当访问首页的时候循环去看cookie里面的内容，找到cookie=token的cookie，并到数据库里面去查这条记录，如果有cookie这条记录则把user(PS:token已被实例化为User)
    @GetMapping("/") //一个反斜杠的时候代表返回默认路径，即templates下的index
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("pagination", paginationDTO); //可以往前端传数据
        return "index";
    }

}
