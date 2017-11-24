package com.gwf.security.web.controller;

import com.gwf.security.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private String folder = "/Users/gaowenfeng/desktop/";

    @PostMapping
    public FileInfo update(MultipartFile file) throws Exception {
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.getSize()+"");

        File localFile = new File(folder,new Date().getTime()+".txt");
       // file.getInputStream()  写到远程
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try (  //jdk7的新语法
            InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream()){

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);  //讲输入流中的内容写到输出流中
            outputStream.flush();
        }catch (Exception e){

        }
    }
}
