package com.SXSQ.project.model.dto.whiteList;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class WhiteListUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 白名单
     */
    private String whiteIp;

    private static final long serialVersionUID = 1L;
}