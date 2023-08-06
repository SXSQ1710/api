import { getInterfaceInfoByIdUsingGET } from '@/services/sx-api/interfaceInfoController';
import { PageContainer, ProCard, ProDescriptions, ProForm, ProFormTextArea } from '@ant-design/pro-components';
import { Button, Divider, Form, message } from 'antd';
import React, { useEffect, useState } from 'react';
import { useParams } from 'umi';


/**
 * 主页
 * @param param0
 * @returns
 */
const Index: React.FC = () => {
  // const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const params = useParams();

  const loadData = async (id: number) => {
    if (!id) {
      message.error('参数不存在')
    }
    // setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGET({
        id
      });
      setData(res.data)
    } catch (error: any) {
      message.error('请求失败' + error.message);
    }
    // setLoading(false);
  }

  useEffect(() => {
    loadData(Number(params.id));
  }, [])

  return (
    <PageContainer title='接口详情'>
      <ProCard colSpan="calc(100% - 580px)">
        <ProDescriptions
          column={1}
          title={data?.name}
          tooltip="接口信息详情"
          extra={<Button>调用</Button>}
        >
          <ProDescriptions.Item label="描述">{data?.description}</ProDescriptions.Item>
          <ProDescriptions.Item label="请求方法">{data?.method}</ProDescriptions.Item>
          <ProDescriptions.Item label="请求参数">{data?.requestParams}</ProDescriptions.Item>
          <ProDescriptions.Item label="请求头">{data?.requestHeader}</ProDescriptions.Item>
          <ProDescriptions.Item label="响应头">{data?.responseHeader}</ProDescriptions.Item>
          <ProDescriptions.Item label="接口状态">{data?.status}</ProDescriptions.Item>
          <ProDescriptions.Item label="创建时间" valueType="dateTime">{data?.createTime}</ProDescriptions.Item>
          <ProDescriptions.Item label="更新时间" valueType="dateTime">{data?.updateTime}</ProDescriptions.Item>
        </ProDescriptions>

      </ProCard>
      <Divider />
      <ProCard colSpan="calc(100% - 580px)">
        <ProForm
          submitter={{
            // 配置按钮的属性
            resetButtonProps: {
              style: {
                // 隐藏重置按钮
                display: 'none',
              },
            },
            submitButtonProps: {
              style: {
                // 隐藏重置按钮
                display: 'none',
              },
            },
          }}
          onFinish={async (values) => console.log(values)}
        >
          <Form.Item>
            <ProFormTextArea
              colProps={{ span: 24 }}
              name="requestParams"
              label="请求参数"
            />
          </Form.Item>
          <Form.Item>
            <Button type='primary' htmlType='submit'>调用</Button>
          </Form.Item>
        </ProForm>
      </ProCard>
      <Divider />
      <ProCard colSpan="calc(100% - 580px)" title='放回结果'>

      </ProCard>
    </PageContainer>
  );
};

export default Index;


