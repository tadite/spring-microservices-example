import React from 'react';
import 'antd/dist/antd.css';
import './index.css';
import { BrowserRouter as Router } from 'react-router-dom';
import Sidebar from './Sidebar';
import Main from './Main';
import { Layout, Menu, Breadcrumb } from 'antd';

const { SubMenu } = Menu;
const { Header, Content, Sider } = Layout;

function App() {
    return (
        <Router>
            <Layout>
                <Layout style={{ height: '100vh' }}>
                    <Sidebar />
                    <Main />
                </Layout>
            </Layout>
        </Router>
    );
}

export default App;
