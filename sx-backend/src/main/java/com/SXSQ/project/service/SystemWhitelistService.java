package com.SXSQ.project.service;

import com.SXSQ.common.model.entity.SystemWhitelist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【system_whitelist(ip白名单)】的数据库操作Service
* @createDate 2023-08-09 10:05:51
*/
public interface SystemWhitelistService extends IService<SystemWhitelist> {

    void validWhiteList(SystemWhitelist whitelist, boolean b);

}
