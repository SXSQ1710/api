package com.SXSQ.project.service;

import com.SXSQ.project.model.entity.InterfaceInfo;
import com.SXSQ.project.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 *
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);
}
