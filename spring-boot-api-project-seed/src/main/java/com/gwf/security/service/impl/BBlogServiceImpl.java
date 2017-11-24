package com.gwf.security.service.impl;

import com.gwf.security.core.AbstractService;
import com.gwf.security.dao.BBlogMapper;
import com.gwf.security.model.BBlog;
import com.gwf.security.service.BBlogService;
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
