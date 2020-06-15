import React from 'react';
import logo from './logo.svg';
import './App.css';
import 'antd/dist/antd.css';

import { Layout, Menu, Breadcrumb } from 'antd';
import { ProjectOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;
const { Header, Content, Sider } = Layout;

function App() {
  return (
      <Layout style={{ height: '100%' }}>
          <Header className="header">
              <div className="logo" />
              <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                  <Menu.Item key="1">nav 1</Menu.Item>
                  <Menu.Item key="2">nav 2</Menu.Item>
                  <Menu.Item key="3">nav 3</Menu.Item>
              </Menu>
          </Header>
          <Layout>
              <Sider width={200} className="site-layout-background">
                  <Menu
                      mode="inline"
                      defaultSelectedKeys={['1']}
                      defaultOpenKeys={['sub1']}
                      style={{ height: '100%', borderRight: 0 }}
                  >
                      <SubMenu key="sub1" icon={<ProjectOutlined />} title="subnav 1">
                          <Menu.Item key="1">Projects</Menu.Item>
                          <Menu.Item key="2">option2</Menu.Item>
                          <Menu.Item key="3">option3</Menu.Item>
                          <Menu.Item key="4">option4</Menu.Item>
                      </SubMenu>
                  </Menu>
              </Sider>
              <Layout style={{ padding: '0 24px 24px' }}>
                  <Breadcrumb style={{ margin: '16px 0' }}>
                      <Breadcrumb.Item>Home</Breadcrumb.Item>
                      <Breadcrumb.Item>List</Breadcrumb.Item>
                      <Breadcrumb.Item>App</Breadcrumb.Item>
                  </Breadcrumb>
                  <Content
                      className="site-layout-background"
                      style={{
                          padding: 24,
                          margin: 0,
                          minHeight: 280,
                      }}
                  >
                      Content
                  </Content>
              </Layout>
          </Layout>
      </Layout>
  );
}

export default App;
