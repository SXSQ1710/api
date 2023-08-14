package com.SXSQ.project.service;

import com.SXSQ.common.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    public void validUserInterfaceInfo(com.SXSQ.common.model.entity.UserInterfaceInfo userInterfaceInfo, boolean add);

    public boolean invokeCount(long interfaceInfoId, long userId);
}
