package com.SXSQ.common.service;


import com.SXSQ.common.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
