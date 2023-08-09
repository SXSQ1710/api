// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** addBlackList POST /api/blackList/add */
export async function addBlackListUsingPOST(
  body: API.BlackListAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponselong>('/api/blackList/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteBlackList POST /api/blackList/delete */
export async function deleteBlackListUsingPOST(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/blackList/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getBlackListById GET /api/blackList/get */
export async function getBlackListByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getBlackListByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseSystemBlacklist>('/api/blackList/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listBlackList GET /api/blackList/list */
export async function listBlackListUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listBlackListUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListSystemBlacklist>('/api/blackList/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listBlackListByPage GET /api/blackList/list/page */
export async function listBlackListByPageUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listBlackListByPageUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageSystemBlacklist>('/api/blackList/list/page', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateBlackList POST /api/blackList/update */
export async function updateBlackListUsingPOST(
  body: API.BlackListUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/blackList/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** addWhiteList POST /api/whiteList/add */
export async function addWhiteListUsingPOST(
  body: API.WhiteListAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponselong>('/api/whiteList/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteWhiteList POST /api/whiteList/delete */
export async function deleteWhiteListUsingPOST(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/whiteList/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getWhiteListById GET /api/whiteList/get */
export async function getWhiteListByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getWhiteListByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseSystemWhitelist>('/api/whiteList/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listWhiteList GET /api/whiteList/list */
export async function listWhiteListUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listWhiteListUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListSystemWhitelist>('/api/whiteList/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listWhiteListByPage GET /api/whiteList/list/page */
export async function listWhiteListByPageUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listWhiteListByPageUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageSystemWhitelist>('/api/whiteList/list/page', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateWhiteList POST /api/whiteList/update */
export async function updateWhiteListUsingPOST(
  body: API.WhiteListUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/whiteList/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
