package com.SXSQ.project.service;

import com.SXSQ.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
