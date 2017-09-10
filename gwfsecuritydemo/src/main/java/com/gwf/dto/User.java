package com.gwf.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@Data
public class User {

    public interface  UserSimpleView{};  //使用jsonview第一步，使用接口声明视图
    public interface  UserDetailView extends  UserSimpleView{};  //

    private String username;
    private String password;

    @JsonView(UserSimpleView.class)  //使用jsonview第二部，在值对象的get方法上指定视图
    public String getUsername(){
        return username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword(){
        return password;
    }


}
