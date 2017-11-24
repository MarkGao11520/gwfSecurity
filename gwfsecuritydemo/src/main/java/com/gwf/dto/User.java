package com.gwf.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gwf.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@Data
public class User {

    public interface  UserSimpleView{};  //使用jsonview第一步，使用接口声明视图
    public interface  UserDetailView extends  UserSimpleView{};  //

    private String id;

    @MyConstraint(message = "这是一个测试")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId(){
        return id;
    }

    @JsonView(UserSimpleView.class)  //使用jsonview第二部，在值对象的get方法上指定视图
    public String getUsername(){
        return username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword(){
        return password;
    }


}
