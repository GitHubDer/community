package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by D on 2020/4/2--19:01
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
