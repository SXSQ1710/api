package com.SXSQ.project.model.dto.blackList;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class BlackListUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 黑名单
     */
    private String blackIp;

    private static final long serialVersionUID = 1L;
}