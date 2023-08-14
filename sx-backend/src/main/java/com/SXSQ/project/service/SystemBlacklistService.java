package com.SXSQ.project.service;

import com.SXSQ.common.model.entity.SystemBlacklist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【system_blacklist(ip黑名单)】的数据库操作Service
* @createDate 2023-08-09 10:05:07
*/
public interface SystemBlacklistService extends IService<SystemBlacklist> {

    void validBlackList(SystemBlacklist blackList, boolean b);
}
