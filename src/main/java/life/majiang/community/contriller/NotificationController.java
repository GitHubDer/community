package life.majiang.community.contriller;

import life.majiang.community.dto.NotificationDTO;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.model.Notification;
import life.majiang.community.model.User;
import life.majiang.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D on 2020/3/13--18:37
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}")
    //String的意思是返回对应的页面
    public String profile(HttpServletRequest request, @PathVariable(name = "id") Long id) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        //校验是否为本人
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }

}
