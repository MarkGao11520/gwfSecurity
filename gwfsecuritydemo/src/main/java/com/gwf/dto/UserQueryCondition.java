package com.gwf.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@Data
public class UserQueryCondition {
   @ApiModelProperty(value = "用户账号")
   private  String username;
   @ApiModelProperty(value = "用户年龄起始值")
   private int age;
   @ApiModelProperty(value = "用户年龄终止值")
   private int ageTo;
}
