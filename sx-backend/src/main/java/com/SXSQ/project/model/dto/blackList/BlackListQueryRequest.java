package com.SXSQ.project.model.dto.blackList;

import com.SXSQ.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlackListQueryRequest extends PageRequest implements Serializable {

    /**
     * 黑名单
     */
    private String blackIp;

    private static final long serialVersionUID = 1L;
}