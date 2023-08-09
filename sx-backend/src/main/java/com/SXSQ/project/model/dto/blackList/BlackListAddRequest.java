package com.SXSQ.project.model.dto.blackList;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class BlackListAddRequest implements Serializable {

    /**
     * 黑名单
     */
    private String blackIp;

    private static final long serialVersionUID = 1L;
}