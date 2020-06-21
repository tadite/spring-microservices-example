import React from "react";
import "./Main.css";
import "antd/dist/antd.css";
import Router from "./Routes";
import { Layout, Menu, Breadcrumb } from "antd";
import { useLocation } from "react-router-dom";

const { Content } = Layout;

function Main() {
  let location = useLocation();
  let pathSteps = location.pathname.split("/");
  return (
    <Layout className="site-layout">
      <Content style={{ margin: "0 16px" }}>
        <Breadcrumb style={{ margin: "16px 0" }}>
          {pathSteps.map((step) => (
            <Breadcrumb.Item>{step}</Breadcrumb.Item>
          ))}
        </Breadcrumb>
        <Content
          className="site-layout-background"
          style={{
            padding: 24,
            margin: 0,
            minHeight: 280,
          }}
        >
          <Router />
        </Content>
      </Content>
    </Layout>
  );
}

export default Main;
