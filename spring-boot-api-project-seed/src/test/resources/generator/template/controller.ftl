package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    @ApiOperation("添加${modelNameUpperCamel}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除${modelNameLowerCamel}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(@ApiParam(value = "${modelNameLowerCamel} id")@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping()
    @ApiOperation("修改${modelNameLowerCamel}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    @ApiOperation(value = "根据id查询${modelNameUpperCamel}详情",response = ${modelNameUpperCamel}.class)
    public Result detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @GetMapping()
    @ApiOperation(value = "分页查询${modelNameUpperCamel}列表",response = ${modelNameUpperCamel}.class,responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 401,message = "权限不足"),
        @ApiResponse(code = 403,message = "不合法的token验证"),
        @ApiResponse(code = 500,message = "服务器内部错误"),
        @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
