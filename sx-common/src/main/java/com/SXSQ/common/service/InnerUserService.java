package com.SXSQ.common.service;

import com.SXSQ.common.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务
 */
public interface InnerUserService {

    /**
     * 数据库中查询是否已经分配给用户密钥
     *
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
