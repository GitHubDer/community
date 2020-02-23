package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * Created by D on 2020/1/2--14:34
 */
@Data
public class
QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer LikeCount;
    private User user;
}
