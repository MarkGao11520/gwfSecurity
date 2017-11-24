package com.gwf.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    //伪造mvc环境
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                        .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello upload".getBytes("utf-8"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //模拟发送请求
        String result =
        mockMvc.perform(get("/user") //发往/user的get请求
                        .param("username","gwf")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))  //utf编码
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()")
                        .value(3))
                .andReturn().getResponse().getContentAsString();  //对返回字符串的json内容进行判断
        log.info(result);
    }

    @Test
    public void whenGenInfoSuccess() throws Exception{
        String result =
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username")
                        .value("tom"))
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void whenGenInfoFail() throws Exception{
        mockMvc.perform(get("/user/a")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception{
        Date date = new Date();
        log.info(date.getTime()+"");
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":\""+date.getTime()+"\"}";
        String result =
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1))
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception{
        Date date = new Date(LocalDateTime
                .now()  //当前时间
                .plusYears(1)  //未来的一年
                .atZone(ZoneId.systemDefault())  //时区
                .toInstant()
                .toEpochMilli());
        log.info(date.getTime()+"");
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":\""+date.getTime()+"\"}";
        String result =
                mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id")
                                .value(1))
                        .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception{
        mockMvc.perform(delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
