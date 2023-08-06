package com.SXSQ.project.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: InterfaceInfoInvokeRequest
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/6 22:03
 **/

@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求接口
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;

}
