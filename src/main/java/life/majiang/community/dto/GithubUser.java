package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by D on 2019/12/21--23:12
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
