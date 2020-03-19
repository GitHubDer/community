package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by D on 2020/3/13--2:24
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
