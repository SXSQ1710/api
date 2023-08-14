package com.SXSQ.project.service.impl.inner;

import com.SXSQ.common.model.entity.InterfaceInfo;
import com.SXSQ.common.model.entity.User;
import com.SXSQ.common.service.InnerInterfaceInfoService;
import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.mapper.InterfaceInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @title: InnerInterfaceInfoServiceImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/14 22:11
 **/

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if(StringUtils.isAllBlank(url,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        queryWrapper.eq("method",method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
