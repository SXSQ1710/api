package com.SXSQ.common.service;

import com.SXSQ.common.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 *
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询调用接口是否存在
     *
     * @param url
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String url, String method);
}
