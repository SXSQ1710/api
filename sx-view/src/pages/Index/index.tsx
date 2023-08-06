import { listInterfaceInfoByPageUsingGET } from '@/services/sx-api/interfaceInfoController';
import { EllipsisOutlined } from '@ant-design/icons';
import { PageContainer, ProList } from '@ant-design/pro-components';
import { Progress, Tag, message } from 'antd';
import React, { useEffect, useState } from 'react';

/**
 * 主页
 * @param param0
 * @returns
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [pageSize, setPageSize] = useState<number>(10);

  const loadData = async (current: number = 1, pageSize: number = 10) => {
    setLoading(true);
    try {
      const res = await listInterfaceInfoByPageUsingGET({
        current, pageSize
      });
      // const list = (res?.data?.records ?? []);
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (error: any) {
      message.error('请求失败' + error.message);
    }
    setLoading(false);
  }

  useEffect(() => {
    loadData();
  }, [])


  return (
    <PageContainer title='在线接口开放平台'>
      <ProList<any>
        loading={loading}
        pagination={{
          showSizeChanger: true,
          pageSize: pageSize,
          total,
          onChange(page, pageSize) {
            setPageSize(pageSize);
            loadData(page, pageSize);
          }
        }}
        metas={{
          title: {},
          subTitle: {},
          type: {},
          avatar: {},
          // content: {},
          actions: {},
        }}
        headerTitle="翻页"
        dataSource={list.map((item) => ({
          title: <a key={item.id} href={`/interface_info/${item.id}`}>{item.name}</a>,
          subTitle: <Tag color="#5BD8A6">已上线</Tag>,
          actions: [
            <a key="invite" href={`/interface_info/${item.id}`}>查看</a>,
          ],
          avatar:
            'https://gw.alipayobjects.com/zos/antfincdn/UCSiy1j6jx/xingzhuang.svg',
        }))}
      />
    </PageContainer>
  );
};

export default Index;
