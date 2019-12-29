package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Create by D on 2019/12/27--15:06
 */
@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}") //#{}代表#{}在mybatis中编译的时候会把形参里面的token放到{}里面去 PS：如果是类的时候会自动放到{}里面去，如果不是形参的时候需要加@Param(加{}里面的内容)注解
    User findByToken(@Param("token") String token);
}
