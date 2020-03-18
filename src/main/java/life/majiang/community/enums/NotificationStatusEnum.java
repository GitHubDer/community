package life.majiang.community.enums;

/**
 * Created by D on 2020/3/12--23:36
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
