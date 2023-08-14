package com.SXSQ.project.service.impl.inner;

import com.SXSQ.common.model.entity.UserInterfaceInfo;
import com.SXSQ.common.service.InnerUserInterfaceInfoService;
import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.mapper.UserInterfaceInfoMapper;
import com.SXSQ.project.service.UserInterfaceInfoService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.annotations.Update;

import javax.annotation.Resource;

/**
 * @title: InnerUserInterfaceInfoServiceImpl
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/14 22:10
 **/

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
