package com.SXSQ.project.service;

import com.SXSQ.common.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * li
 *
 * @description 针对表【post(帖子)】的数据库操作Service
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add  是否为创建校验
     */
    void validPost(Post post, boolean add);
}
