package com.gwf.dao;

import com.gwf.model.BBlog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/12/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestTkMybatis {

    @Autowired
    private BBlogMapper bBlogMapper;

    @Test
    public void testMybatis(){
        Condition condition = new Condition(BBlog.class);
        BBlog bBlog = new BBlog();
        bBlog.setKeyword("aaa");
        bBlog.setContent("bbb");
        condition
                .createCriteria()
                .andEqualTo(bBlog)
                .andEqualTo("uid","1")
                .andBetween("id","1","10")
                .andLessThan("releaseTime",new Date())
                .andGreaterThanOrEqualTo("releaseTime",new Date())
                .andIsNotNull("content")
                .andLike("coverUrl","http");
        ;
        List<BBlog> result = bBlogMapper.selectByCondition(condition);
    }
}
