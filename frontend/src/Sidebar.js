import React from "react";
import "antd/dist/antd.css";
import "./index.css";
import { Layout, Menu } from "antd";
import { NavLink } from "react-router-dom";

const { Sider } = Layout;

function Sidebar() {
  return (
    <Sider width={200} className="site-layout-background">
      <Menu
        mode="inline"
        defaultSelectedKeys={["1"]}
        defaultOpenKeys={["sub1"]}
        style={{ height: "100%", borderRight: 0 }}
      >
        <Menu.Item key="1">
          <NavLink to="/statistics" activeClassName="is-active" exact={true}>
            Statistics
          </NavLink>
        </Menu.Item>
        <Menu.Item key="2">
          <NavLink to="/projects" activeClassName="is-active" exact={true}>
            Projects
          </NavLink>
        </Menu.Item>
        <Menu.Item key="3">
          <NavLink to="/add-project" activeClassName="is-active" exact={true}>
            Add project
          </NavLink>
        </Menu.Item>
        <Menu.Item key="4">
          <NavLink to="/add-entry" activeClassName="is-active" exact={true}>
            Add work entry
          </NavLink>
        </Menu.Item>
        <Menu.Item key="5">
          <NavLink to="/notifications" activeClassName="is-active" exact={true}>
            Notifications
          </NavLink>
        </Menu.Item>
      </Menu>
    </Sider>
  );
}

export default Sidebar;
