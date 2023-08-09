import {
  ProColumns,
  ProFormInstance,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import { Modal } from 'antd';
import React, { useEffect, useRef } from 'react';

export type props = {
  values: API.SystemBlacklist;
  colums: ProColumns<API.SystemBlacklist>[];
  onCancel: () => void;
  onSubmit: (values: API.SystemBlacklist) => Promise<void>;
  visible: boolean;
};
const UpdateModal: React.FC<props> = (props) => {
  const { values, colums, visible, onCancel, onSubmit } = props

  const formRef = useRef<ProFormInstance>();
  useEffect(() => {
    if (formRef) {
      formRef?.current?.setFieldsValue(values);
    }
  }, [values])


  return (
    <Modal open={visible} footer={null} onCancel={() => onCancel?.()}>
      <ProTable<API.SystemBlacklist>
        form={{
          initialValues: values
        }}
        formRef={formRef}
        type='form'
        columns={colums}
        onSubmit={async (value) => {
          onSubmit?.(value)
        }} />
    </Modal>
  );
};
export default UpdateModal;
