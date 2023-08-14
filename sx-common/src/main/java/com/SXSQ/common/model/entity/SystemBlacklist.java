package com.SXSQ.common.model.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ip黑名单
 * @TableName system_blacklist
 */
@TableName(value ="system_blacklist")
@Data
public class SystemBlacklist implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 黑名单
     */
    private String blackIp;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}