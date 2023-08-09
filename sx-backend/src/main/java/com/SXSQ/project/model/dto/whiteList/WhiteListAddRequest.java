package com.SXSQ.project.model.dto.whiteList;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class WhiteListAddRequest implements Serializable {

    /**
     * 白名单
     */
    private String whiteIp;

    private static final long serialVersionUID = 1L;
}