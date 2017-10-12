package com.gwf.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gwf.dto.User;
import com.gwf.dto.UserQueryCondition;
import com.gwf.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    /**
     * 创建用户
     * @param user
     * @param errors
     * @return
     */
    @PostMapping
    public User create(@RequestBody @Valid User user,
                       BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> log.info(error.getDefaultMessage()));
        }
        log.info(user.getId());
        log.info(user.getUsername());
        log.info(user.getPassword());
        log.info(user.getBirthday()+"");
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@RequestBody @Valid User user,
                       BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
              //  FieldError fieldError = (FieldError) error;
              //  String message = fieldError.getField()+" "+error.getDefaultMessage();
                log.info(error.getDefaultMessage());
            });
        }
        log.info(user.getId());
        log.info(user.getUsername());
        log.info(user.getPassword());
        log.info(user.getBirthday()+"");
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        log.info(id);
    }

    /**
     * 用户查询
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation("用户查询服务")  //替换默认接口名/方法名
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1,size = 10,sort = "username desc") Pageable pageable) throws IOException {
        log.info(condition.getUsername());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
//        throw new IOException("读取文件异常");
        return users;
    }

    /**
     * 用户详情的获取
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")  //正则表达式控制只能接受数字
    @JsonView(User.UserDetailView.class)  //使用jsonview第三步,在Controller方法上指定视图
    public User getInfo(@ApiParam(value = "用户id")@PathVariable String id){
        log.info(id);
        User user = new User();
        user.setUsername("tom");
        log.info("进入getInfo服务");
        return user;
      //  throw new UserNotExistException(id,"用户信息不存在");
    }
}
