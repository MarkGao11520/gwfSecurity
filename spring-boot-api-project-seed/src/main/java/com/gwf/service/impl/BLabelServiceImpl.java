package com.gwf.service.impl;

import com.gwf.dao.BLabelMapper;
import com.gwf.model.BLabel;
import com.gwf.service.BLabelService;
import com.gwf.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/11/25.
 */
@Service
@Transactional
public class BLabelServiceImpl extends AbstractService<BLabel> implements BLabelService {
    @Resource
    private BLabelMapper bLabelMapper;

}
