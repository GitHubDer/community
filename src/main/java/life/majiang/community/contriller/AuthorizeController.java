package life.majiang.community.contriller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired //能自动地去把spring容器里面写好的实例化的实例加载到当前使用的上下文
    private GithubProvider githubProvider;

    @Value("${github.client.id}") //Value的意思是会自动的去配置文件application.properties里面读取相对应的id的key的value
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;
    
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //Github登录成功以后，进行判断并获取到用户信息，获取到用户信息之后生成一个token，生成token之后把token放到user对象里面，存储到数据库中，并且把token放到cookie里面。
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null) { //用户不为空并且用户里面的内容不为空
            //userMapper.insert(new User()); 用快捷键Ctrl+Alt+V能自动拖取一个变量生成User user = new User();userMapper.insert(user);
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            //登录成功，写cookie和session
            return "redirect:/"; //加了redirect前缀的话会把地址全部去掉直接跳转重定向到index页面去
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
