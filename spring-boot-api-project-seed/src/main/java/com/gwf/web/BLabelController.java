package com.gwf.web;
import com.gwf.core.Result;
import com.gwf.core.ResultGenerator;
import com.gwf.model.BLabel;
import com.gwf.service.BLabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/11/25.
*/
@RestController
@RequestMapping("/b/label")
public class BLabelController {
    @Resource
    private BLabelService bLabelService;

    @PostMapping
    @ApiOperation("添加BLabel")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BLabel bLabel) {
        bLabelService.save(bLabel);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除bLabel")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(@ApiParam(value = "bLabel id")@PathVariable Integer id) {
        bLabelService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping()
    @ApiOperation("修改bLabel")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(BLabel bLabel) {
        bLabelService.update(bLabel);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    @ApiOperation(value = "根据id查询BLabel详情",response = BLabel.class)
    public Result detail(@RequestParam Integer id) {
        BLabel bLabel = bLabelService.findById(id);
        return ResultGenerator.genSuccessResult(bLabel);
    }

    @GetMapping()
    @ApiOperation(value = "分页查询BLabel列表",response = BLabel.class,responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BLabel> list = bLabelService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
