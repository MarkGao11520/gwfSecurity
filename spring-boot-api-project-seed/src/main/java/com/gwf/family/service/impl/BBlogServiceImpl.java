package com.gwf.family.service.impl;

import com.gwf.family.dao.BBlogMapper;
import com.gwf.family.model.BBlog;
import com.gwf.family.service.BBlogService;
import com.gwf.family.core.AbstractService;
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
