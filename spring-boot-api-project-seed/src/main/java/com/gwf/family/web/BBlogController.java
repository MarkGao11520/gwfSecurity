package com.gwf.family.web;
import com.gwf.family.core.Result;
import com.gwf.family.core.ResultGenerator;
import com.gwf.family.core.ServiceException;
import com.gwf.family.model.BBlog;
import com.gwf.family.service.BBlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/11/20.
*/
@RestController
@RequestMapping("/b/blog")
public class BBlogController {

    @Resource
    private BBlogService bBlogService;

    @PostMapping
    @ApiOperation("添加BBlog")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BBlog bBlog) {
        bBlogService.save(bBlog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除bBlog")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(@ApiParam(value = "bBlog id")@PathVariable Integer id) {
        bBlogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping()
    @ApiOperation("修改bBlog")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(BBlog bBlog) {
        bBlogService.update(bBlog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    @ApiOperation(value = "根据id查询BBlog详情",response = BBlog.class)
    public Result detail(@RequestParam Integer id) {
        BBlog bBlog = bBlogService.findById(id);
        return ResultGenerator.genSuccessResult(bBlog);
    }

    @GetMapping()
    @ApiOperation(value = "分页查询BBlog列表",response = BBlog.class,responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BBlog> list = bBlogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
    //    return ResultGenerator.genSuccessResult(pageInfo);
        throw new ServiceException("ssss");
    }
}
