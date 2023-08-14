package com.SXSQ.project.service.impl;

import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.mapper.SystemBlacklistMapper;
import com.SXSQ.common.model.entity.SystemBlacklist;
import com.SXSQ.project.service.SystemBlacklistService;
import com.SXSQ.project.utils.IpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author Administrator
* @description 针对表【system_blacklist(ip黑名单)】的数据库操作Service实现
* @createDate 2023-08-09 10:05:07
*/
@Service
public class SystemBlacklistServiceImpl extends ServiceImpl<SystemBlacklistMapper, SystemBlacklist>
    implements SystemBlacklistService {

    @Override
    public void validBlackList(SystemBlacklist blackList, boolean b) {

        String blackIp = blackList.getBlackIp();
        if (!IpUtil.isValidIPV4ByCustomRegex(blackIp)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法IP地址！");
        }

    }
}




