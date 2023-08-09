import {
  ProColumns,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Modal} from 'antd';
import React from 'react';

export type props = {
  colums: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<boolean>;
  visible: boolean;
};
const CreateModal: React.FC<props> = (props) => {
  const {colums, visible, onCancel, onSubmit} = props
  return (
    <Modal open={visible} footer={null} onCancel={() => onCancel?.()}>
      <ProTable<API.InterfaceInfo> type='form' columns={colums} onSubmit={async (value) => {
        onSubmit?.(value)
      }}/>
    </Modal>
  );
};
export default CreateModal;
