package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by D on 2020/1/10--13:49
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
