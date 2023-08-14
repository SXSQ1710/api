package com.SXSQ.project.service.impl.inner;

import com.SXSQ.common.model.entity.User;
import com.SXSQ.common.service.InnerUserService;
import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.mapper.UserMapper;
import com.SXSQ.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @title: InnerUserServiceImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/14 22:10
 **/

@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if(StringUtils.isAllBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey",accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
