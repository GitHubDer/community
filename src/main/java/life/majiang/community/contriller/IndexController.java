package life.majiang.community.contriller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller //加这个的话会自动识别扫描当前的类，把它作为spring的一个bean去管理 同时也识别它为一个Controller,Controller的意思就是允许这个类去接收前端的请求
public class IndexController {

//    @GetMapping("/hello") //相当于访问/templates/index.html
//    public String hello(@RequestParam(name = "name", required=false, defaultValue="World") String name, Model model) { //请求的参数
//        model.addAttribute("name", name);
//        return "hello";
//    }

    @Autowired
    private UserMapper userMapper;

    //当访问首页的时候循环去看cookie里面的内容，找到cookie=token的cookie，并到数据库里面去查这条记录，如果有cookie这条记录则把user(PS:token已被实例化为User)
    @GetMapping("/") //一个反斜杠的时候代表返回默认路径，即templates下的index
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        //for循环数组，看看for循环里面的key
        if (cookies != null) {
            for (Cookie cookie : cookies) { //看看cookie里面都有什么东西
                if (cookie.getName().equals("token")) { //看看cookie里面的name是不是等于token
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token); //此时已经拿到token了，到数据库里面去查有没有这条记录
                    if (user != null) { //如果user不为空的时候就把user放到session里面去，让页面能够判断是否登录成功以便展示"我"还是"登录"
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        return "index";
    }

}
