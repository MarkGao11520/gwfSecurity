package com.gwf.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
        String content = "{\"username\":\"tom\",\"password\":\"123\"}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1));
    }
}
