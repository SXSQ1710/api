import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns, ProDescriptionsItemProps } from '@ant-design/pro-components';
import {
	FooterToolbar,
	ModalForm,
	PageContainer,
	ProDescriptions,
	ProFormText,
	ProFormTextArea,
	ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import { Button, Drawer, message } from 'antd';
import React, { useRef, useState } from 'react';

import CreateModal from './components/CreateModal';
import UpdateModal from './components/UpdateModal';
import { addBlackListUsingPOST, addWhiteListUsingPOST, deleteBlackListUsingPOST, deleteWhiteListUsingPOST, listBlackListByPageUsingGET, listWhiteListByPageUsingGET, onlineInterfaceUsingPOST, updateBlackListUsingPOST, updateWhiteListUsingPOST } from '@/services/sx-api/blackWhiteListController';

const TableList: React.FC = () => {
	/**
	 * @en-US Pop-up window of new window
	 * @zh-CN 新建窗口的弹窗
	 *  */
	const [createBlackListModalOpen, handleCreatBlackListModalOpen] = useState<boolean>(false);
	/**
	 * @en-US The pop-up window of the distribution update window
	 * @zh-CN 分布更新窗口的弹窗
	 * */
	const [updateBlackListModalOpen, handleUpdateBlackListModalOpen] = useState<boolean>(false);
	const [createWhiteListModalOpen, handleCreatWhiteListModalOpen] = useState<boolean>(false);
	const [updateWhiteListModalOpen, handleUpdateWhiteListModalOpen] = useState<boolean>(false);
	const [showDetail, setShowDetail] = useState<boolean>(false);
	const blackListactionRef = useRef<ActionType>();
	const whiteListactionRef = useRef<ActionType>();
	const [currentBlackListRow, setCurrentBlackListRow] = useState<API.SystemBlacklist>();
	const [currentWhiteListRow, setCurrentWhiteListRow] = useState<API.SystemWhitelist>();
	const [selectedRowsState, setSelectedRows] = useState<API.SystemBlacklist[]>([]);

	/**
	 * @en-US Update node
	 * @zh-CN 更新节点
	 *
	 * @param fields
	 */
	const blackListUpdate = async (fields: API.SystemBlacklist) => {
		if (!currentBlackListRow) {
			return;
		}
		const hide = message.loading('修改中');
		try {
			await updateBlackListUsingPOST({
				id: currentBlackListRow.id,
				...fields
			});
			hide();
			message.success('操作成功');
			blackListactionRef.current?.reload();
			return true;
		} catch (error: any) {
			hide();
			message.error('操作失败' + error.message);
			return false;
		}
	};

	/**
 *  Delete node
 * @zh-CN 删除节点
 *
 * @param record
 */
	const blackListRemove = async (record: API.SystemBlacklist) => {
		const hide = message.loading('正在删除');
		if (!record) return true;
		try {
			await deleteBlackListUsingPOST({
				id: record.id
			});
			hide();
			message.success('删除成功');
			blackListactionRef.current?.reload();
			return true;
		} catch (error: any) {
			hide();
			message.error('删除失败' + error.message);
			return false;
		}
	};

	/**
 * @en-US Add node
 * @zh-CN 添加节点
 * @param fields
 */
	const blackListAdd = async (fields: API.SystemBlacklist) => {
		const hide = message.loading('正在添加');
		try {
			await addBlackListUsingPOST({
				...fields,
			});
			hide();
			message.success('创建成功');
			handleUpdateBlackListModalOpen(false)
			blackListactionRef.current?.reload();
			handleCreatBlackListModalOpen(false);
			return true;
		} catch (error: any) {
			hide();
			message.error('创建失败，' + error.message);
			return false;
		}
	};


	/**
	 * @en-US Update node
	 * @zh-CN 更新节点
	 *
	 * @param fields
	 */
	const whiteListUpdate = async (fields: API.SystemWhitelist) => {
		if (!currentWhiteListRow) {
			return;
		}
		const hide = message.loading('修改中');
		try {
			await updateWhiteListUsingPOST({
				id: currentWhiteListRow.id,
				...fields
			});
			hide();
			message.success('操作成功');
			whiteListactionRef.current?.reload();
			return true;
		} catch (error: any) {
			hide();
			message.error('操作失败' + error.message);
			return false;
		}
	};

	/**
 *  Delete node
 * @zh-CN 删除节点
 *
 * @param record
 */
	const whiteListRemove = async (record: API.SystemWhitelist) => {
		const hide = message.loading('正在删除');
		if (!record) return true;
		try {
			await deleteWhiteListUsingPOST({
				id: record.id
			});
			hide();
			message.success('删除成功');
			whiteListactionRef.current?.reload();
			return true;
		} catch (error: any) {
			hide();
			message.error('删除失败' + error.message);
			return false;
		}
	};

	/**
 * @en-US Add node
 * @zh-CN 添加节点
 * @param fields
 */
	const whiteListAdd = async (fields: API.SystemWhitelist) => {
		const hide = message.loading('正在添加');
		try {
			await addWhiteListUsingPOST({
				...fields,
			});
			hide();
			message.success('创建成功');
			handleUpdateWhiteListModalOpen(false)
			whiteListactionRef.current?.reload();
			handleCreatWhiteListModalOpen(false);
			return true;
		} catch (error: any) {
			hide();
			message.error('创建失败，' + error.message);
			return false;
		}
	};

	const blacklistColumns: ProColumns<API.SystemBlacklist>[] = [
		{
			title: 'id',
			dataIndex: 'id',
			valueType: 'index',
		},
		{
			title: 'ip地址',
			dataIndex: 'blackIp',
			valueType: 'text',
			formItemProps: {
				rules: [{
					required: true,
				}]
			},
		},
		{
			title: '创建时间',
			dataIndex: 'createTime',
			valueType: 'dateTime',
			hideInForm: true,
		},
		{
			title: '更新时间',
			dataIndex: 'updateTime',
			valueType: 'dateTime',
			hideInForm: true,
		},
		{
			title: '操作',
			dataIndex: 'option',
			valueType: 'option',
			render: (_, record) => [
				<a
					key="config"
					onClick={() => {
						handleUpdateBlackListModalOpen(true);
						setCurrentBlackListRow(record);
					}}
				>
					修改
				</a>,
				<Button key="config"
					type="text"
					danger
					onClick={() => {
						blackListRemove(record);
					}}>
					删除
				</Button>,
			],
		},
	];

	const whitelistColumns: ProColumns<API.SystemWhitelist>[] = [
		{
			title: 'id',
			dataIndex: 'id',
			valueType: 'index',
		},
		{
			title: 'ip地址',
			dataIndex: 'whiteIp',
			valueType: 'text',
			formItemProps: {
				rules: [{
					required: true,
				}]
			},
		},
		{
			title: '创建时间',
			dataIndex: 'createTime',
			valueType: 'dateTime',
			hideInForm: true,
		},
		{
			title: '更新时间',
			dataIndex: 'updateTime',
			valueType: 'dateTime',
			hideInForm: true,
		},
		{
			title: '操作',
			dataIndex: 'option',
			valueType: 'option',
			render: (_, record) => [
				<a
					key="config"
					onClick={() => {
						handleUpdateWhiteListModalOpen(true);
						setCurrentWhiteListRow(record);
					}}
				>
					修改
				</a>,
				<Button key="config"
					type="text"
					danger
					onClick={() => {
						whiteListRemove(record);
					}}>
					删除
				</Button>,
			],
		},
	];


	return (
		<PageContainer>
			<ProTable <API.SystemBlacklist>
				headerTitle={'黑名单'}
				actionRef={blackListactionRef}
				search={{
					labelWidth: 120,
				}}
				toolBarRender={() => [
					<Button
						type="primary"
						// key="primary"
						onClick={() => {
							handleCreatBlackListModalOpen(true);
						}}
					>
						<PlusOutlined /> 新建
					</Button>,
				]}
				columns={blacklistColumns}
				request={async (
					// 第一个参数 params 查询表单和 params 参数的结合
					// 第一个参数中一定会有 pageSize 和  current ，这两个参数是 antd 的规范
					params,
					sort,
					filter,
				) => {
					// 这里需要返回一个 Promise,在返回之前你可以进行数据转化
					// 如果需要转化参数可以在这里进行修改
					const res: any = await listBlackListByPageUsingGET({
						...params
					});
					if (res.data) {
						return {
							data: res?.data.records,
							success: true,
							total: res?.data.total,
						}
					} else {
						return {
							data: [],
							success: false,
							total: 0,
						}
					}
				}}
				rowKey="id"
			/>
			<UpdateModal
				colums={blacklistColumns}
				onSubmit={async (value) => {
					console.log(value.id);
					const success = await blackListUpdate(value);
					if (success) {
						handleUpdateBlackListModalOpen(false);
						setCurrentBlackListRow(undefined);
						if (blackListactionRef.current) {
							blackListactionRef.current.reload();
						}
					}
				}}
				onCancel={() => {
					handleUpdateBlackListModalOpen(false);
					if (!showDetail) {
						setCurrentBlackListRow(undefined);
					}
				}}
				visible={updateBlackListModalOpen}
				values={currentBlackListRow || {}}
			/>
			<CreateModal
				// key='CreateModal'
				colums={blacklistColumns}
				onCancel={() => {
					handleCreatBlackListModalOpen(false)
				}}
				onSubmit={(values) => blackListAdd(values)}
				visible={createBlackListModalOpen}
			/>

 


			<ProTable <API.SystemWhitelist>
				headerTitle={'白名单'}
				actionRef={whiteListactionRef}
				search={{
					labelWidth: 120,
				}}
				toolBarRender={() => [
					<Button
						type="primary"
						// key="primary"
						onClick={() => {
							handleCreatWhiteListModalOpen(true);
						}}
					>
						<PlusOutlined /> 新建
					</Button>,
				]}
				columns={whitelistColumns}
				request={async (
					// 第一个参数 params 查询表单和 params 参数的结合
					// 第一个参数中一定会有 pageSize 和  current ，这两个参数是 antd 的规范
					params,
					sort,
					filter,
				) => {
					// 这里需要返回一个 Promise,在返回之前你可以进行数据转化
					// 如果需要转化参数可以在这里进行修改
					const res: any = await listWhiteListByPageUsingGET({
						...params
					});
					if (res.data) {
						return {
							data: res?.data.records,
							success: true,
							total: res?.data.total,
						}
					} else {
						return {
							data: [],
							success: false,
							total: 0,
						}
					}
				}}
				rowKey="id"
			/>
			<UpdateModal
				colums={whitelistColumns}
				onSubmit={async (value) => {
					console.log(value.id);
					const success = await whiteListUpdate(value);
					if (success) {
						handleUpdateWhiteListModalOpen(false);
						setCurrentWhiteListRow(undefined);
						if (whiteListactionRef.current) {
							whiteListactionRef.current.reload();
						}
					}
				}}
				onCancel={() => {
					handleUpdateWhiteListModalOpen(false);
					if (!showDetail) {
						setCurrentWhiteListRow(undefined);
					}
				}}
				visible={updateWhiteListModalOpen}
				values={currentWhiteListRow || {}}
			/>
			<CreateModal
				// key='CreateModal'
				colums={whitelistColumns}
				onCancel={() => {
					handleCreatWhiteListModalOpen(false)
				}}
				onSubmit={(values) => whiteListAdd(values)}
				visible={createWhiteListModalOpen}
			/>
		</PageContainer>
	);
};
export default TableList;
