package com.gwf.service.impl;

import com.gwf.core.AbstractService;
import com.gwf.dao.BBlogMapper;
import com.gwf.model.BBlog;
import com.gwf.service.BBlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/11/20.
 */
@Service
@Transactional
public class BBlogServiceImpl extends AbstractService<BBlog> implements BBlogService {
    @Resource
    private BBlogMapper bBlogMapper;

}
