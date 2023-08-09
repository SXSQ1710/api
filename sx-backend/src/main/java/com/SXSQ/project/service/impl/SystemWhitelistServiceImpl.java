package com.SXSQ.project.service.impl;

import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.mapper.SystemWhitelistMapper;
import com.SXSQ.project.model.entity.SystemWhitelist;
import com.SXSQ.project.service.SystemWhitelistService;
import com.SXSQ.project.utils.IpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【system_whitelist(ip白名单)】的数据库操作Service实现
* @createDate 2023-08-09 10:05:51
*/
@Service
public class SystemWhitelistServiceImpl extends ServiceImpl<SystemWhitelistMapper, SystemWhitelist>
    implements SystemWhitelistService {

    @Override
    public void validWhiteList(SystemWhitelist whitelist, boolean b) {
        String whiteIp = whitelist.getWhiteIp();
        if (!IpUtil.isValidIPV4ByCustomRegex(whiteIp)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法IP地址！");
        }
    }

}




