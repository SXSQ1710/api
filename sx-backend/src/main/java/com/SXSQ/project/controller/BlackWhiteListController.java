package com.SXSQ.project.controller;

import com.SXSQ.project.annotation.AuthCheck;
import com.SXSQ.project.common.BaseResponse;
import com.SXSQ.project.common.DeleteRequest;
import com.SXSQ.project.common.ErrorCode;
import com.SXSQ.project.common.ResultUtils;
import com.SXSQ.project.constant.CommonConstant;
import com.SXSQ.project.exception.BusinessException;
import com.SXSQ.project.model.dto.blackList.BlackListUpdateRequest;
import com.SXSQ.project.model.dto.blackList.BlackListAddRequest;
import com.SXSQ.project.model.dto.blackList.BlackListQueryRequest;
import com.SXSQ.project.model.dto.blackList.BlackListUpdateRequest;
import com.SXSQ.project.model.dto.whiteList.WhiteListAddRequest;
import com.SXSQ.project.model.dto.whiteList.WhiteListQueryRequest;
import com.SXSQ.project.model.dto.whiteList.WhiteListUpdateRequest;
import com.SXSQ.project.model.entity.SystemBlacklist;
import com.SXSQ.project.model.entity.SystemWhitelist;
import com.SXSQ.project.model.entity.User;
import com.SXSQ.project.service.SystemBlacklistService;
import com.SXSQ.project.service.SystemWhitelistService;
import com.SXSQ.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxsq.sxclientsdk.client.SxApiClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class BlackWhiteListController {
    @Resource
    private SystemBlacklistService systemBlacklistService;

    @Resource
    private SystemWhitelistService systemWhitelistService;

    @Resource
    private UserService userService;

    @Resource
    private SxApiClient sxApiClient;

    // region 增删改查

    /**
     * 创建
     *
     * @param blackListAddRequest
     * @param request
     * @return
     */
    @PostMapping("/blackList/add")
    public BaseResponse<Long> addBlackList(@RequestBody BlackListAddRequest blackListAddRequest, HttpServletRequest request) {
        if (blackListAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemBlacklist blackList = new SystemBlacklist();
        BeanUtils.copyProperties(blackListAddRequest, blackList);
        // 校验
        systemBlacklistService.validBlackList(blackList, true);

        boolean result = systemBlacklistService.save(blackList);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newBlackListId = blackList.getId();
        return ResultUtils.success(newBlackListId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/blackList/delete")
    public BaseResponse<Boolean> deleteBlackList(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        SystemBlacklist oldBlackList = systemBlacklistService.getById(id);
        if (oldBlackList == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = systemBlacklistService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param blackListUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/blackList/update")
    public BaseResponse<Boolean> updateBlackList(@RequestBody BlackListUpdateRequest blackListUpdateRequest,
                                                 HttpServletRequest request) {
        if (blackListUpdateRequest == null || blackListUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemBlacklist blackList = new SystemBlacklist();
        BeanUtils.copyProperties(blackListUpdateRequest, blackList);
        // 参数校验
        systemBlacklistService.validBlackList(blackList, false);
        User user = userService.getLoginUser(request);
        long id = blackListUpdateRequest.getId();
        // 判断是否存在
        SystemBlacklist oldBlackList = systemBlacklistService.getById(id);
        if (oldBlackList == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = systemBlacklistService.updateById(blackList);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/blackList/get")
    public BaseResponse<SystemBlacklist> getBlackListById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemBlacklist blackList = systemBlacklistService.getById(id);
        return ResultUtils.success(blackList);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param blackListQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/blackList/list")
    public BaseResponse<List<SystemBlacklist>> listBlackList(BlackListQueryRequest blackListQueryRequest) {
        SystemBlacklist blackListQuery = new SystemBlacklist();
        if (blackListQueryRequest != null) {
            BeanUtils.copyProperties(blackListQueryRequest, blackListQuery);
        }
        QueryWrapper<SystemBlacklist> queryWrapper = new QueryWrapper<>(blackListQuery);
        List<SystemBlacklist> blackList = systemBlacklistService.list(queryWrapper);
        return ResultUtils.success(blackList);
    }

    /**
     * 分页获取列表
     *
     * @param blackListQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/blackList/list/page")
    public BaseResponse<Page<SystemBlacklist>> listBlackListByPage(BlackListQueryRequest blackListQueryRequest, HttpServletRequest request) {
        if (blackListQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemBlacklist blackListQuery = new SystemBlacklist();
        BeanUtils.copyProperties(blackListQueryRequest, blackListQuery);
        long current = blackListQueryRequest.getCurrent();
        long size = blackListQueryRequest.getPageSize();
        String sortField = blackListQueryRequest.getSortField();
        String sortOrder = blackListQueryRequest.getSortOrder();
        // 需支持模糊搜索
        String blackIp = blackListQuery.getBlackIp();
        blackListQuery.setBlackIp(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<SystemBlacklist> queryWrapper = new QueryWrapper<>(blackListQuery);
        queryWrapper.like(StringUtils.isNotBlank(blackIp), "blackIp", blackIp);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<SystemBlacklist> blackListPage = systemBlacklistService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(blackListPage);
    }


    /**
     * 创建
     *
     * @param whiteListAddRequest
     * @param request
     * @return
     */
    @PostMapping("/whiteList/add")
    public BaseResponse<Long> addWhiteList(@RequestBody WhiteListAddRequest whiteListAddRequest, HttpServletRequest request) {
        if (whiteListAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemWhitelist whitelist = new SystemWhitelist();
        BeanUtils.copyProperties(whiteListAddRequest, whitelist);
        // 校验
        systemWhitelistService.validWhiteList(whitelist, true);

        boolean result = systemWhitelistService.save(whitelist);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newWhiteListId = whitelist.getId();
        return ResultUtils.success(newWhiteListId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/whiteList/delete")
    public BaseResponse<Boolean> deleteWhiteList(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        SystemWhitelist oldWhiteList = systemWhitelistService.getById(id);
        if (oldWhiteList == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = systemWhitelistService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param whiteListUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/whiteList/update")
    public BaseResponse<Boolean> updateWhiteList(@RequestBody WhiteListUpdateRequest whiteListUpdateRequest,
                                                 HttpServletRequest request) {
        if (whiteListUpdateRequest == null || whiteListUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemWhitelist whiteList = new SystemWhitelist();
        BeanUtils.copyProperties(whiteListUpdateRequest, whiteList);
        // 参数校验
        systemWhitelistService.validWhiteList(whiteList, false);
        User user = userService.getLoginUser(request);
        long id = whiteListUpdateRequest.getId();
        // 判断是否存在
        SystemWhitelist oldWhiteList = systemWhitelistService.getById(id);
        if (oldWhiteList == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = systemWhitelistService.updateById(whiteList);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/whiteList/get")
    public BaseResponse<SystemWhitelist> getWhiteListById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemWhitelist whiteList = systemWhitelistService.getById(id);
        return ResultUtils.success(whiteList);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param whiteListQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/whiteList/list")
    public BaseResponse<List<SystemWhitelist>> listWhiteList(WhiteListQueryRequest whiteListQueryRequest) {
        SystemWhitelist whiteListQuery = new SystemWhitelist();
        if (whiteListQueryRequest != null) {
            BeanUtils.copyProperties(whiteListQueryRequest, whiteListQuery);
        }
        QueryWrapper<SystemWhitelist> queryWrapper = new QueryWrapper<>(whiteListQuery);
        List<SystemWhitelist> whiteList = systemWhitelistService.list(queryWrapper);
        return ResultUtils.success(whiteList);
    }


    /**
     * @param whiteListQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/whiteList/list/page")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Page<SystemWhitelist>> listWhiteListByPage(WhiteListQueryRequest whiteListQueryRequest, HttpServletRequest request) {
        if (whiteListQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SystemWhitelist whiteListQuery = new SystemWhitelist();
        BeanUtils.copyProperties(whiteListQueryRequest, whiteListQuery);
        long current = whiteListQueryRequest.getCurrent();
        long size = whiteListQueryRequest.getPageSize();
        String sortField = whiteListQueryRequest.getSortField();
        String sortOrder = whiteListQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<SystemWhitelist> queryWrapper = new QueryWrapper<>(whiteListQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<SystemWhitelist> whiteListPage = systemWhitelistService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(whiteListPage);
    }
}
