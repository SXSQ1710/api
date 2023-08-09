package com.SXSQ.project.model.dto.whiteList;

import com.SXSQ.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WhiteListQueryRequest extends PageRequest implements Serializable {

    /**
     * 白名单
     */
    private String whiteIp;

    private static final long serialVersionUID = 1L;
}