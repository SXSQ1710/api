export default [
  {
    path: '/user',
    layout: false,
    routes: [{name: '登录', path: '/user/login', component: './User/Login'}],
  },
  { path: '/', name: '主页', icon: 'smile', component: './Index/index' },
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      {name: '接口管理', icon: 'table', path: '/admin/interface', component: './Admin/InterfaceInfo'},
      // { path: '/admin/sub-page', name: '二级管理页', component: './Admin' },
    ],
  },
  {path: '*', layout: false, component: './404'},
];
