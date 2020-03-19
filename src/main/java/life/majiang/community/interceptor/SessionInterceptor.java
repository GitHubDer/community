package life.majiang.community.interceptor;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by D on 2020/1/7--20:27
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private NotificationService notificationService;

    // 可以对所有的地址进行拦截，请求地址的时候，统一去通过cookie拿到之前定义好的token在数据库中查出User对象，放到session里面
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验Coolie 从Cookie里面拿到session，再拿到user，把user写到session里面
        Cookie[] cookies = request.getCookies();
        //for循环数组，看看for循环里面的key
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) { //看看cookie里面都有什么东西
                if (cookie.getName().equals("token")) { //看看cookie里面的name是不是等于token
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token); //能自动拼接token到criteria上面
                    List<User> users = userMapper.selectByExample(userExample);
                    if (users.size() != 0) { //如果user不为空的时候就把user放到session里面去，让页面能够判断是否登录成功以便展示"我"还是"登录"
                        request.getSession().setAttribute("user", users.get(0));
                        Long unreadCount = notificationService.unreadCount(users.get(0).getId());
                        request.getSession().setAttribute("unreadCount", unreadCount);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
