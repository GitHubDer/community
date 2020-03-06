package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by D on 2020/3/4--1:57
 */

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
