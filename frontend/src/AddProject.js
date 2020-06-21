import React from "react";
import ReactDOM from "react-dom";
import "antd/dist/antd.css";
import "./index.css";
import { Form, Input, Button, Checkbox } from "antd";

const { TextArea } = Input;


export default function AddProject() {
  const onFinish = (values) => {
    console.log("Success:", values);
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <Form
      name="basic"
      initialValues={{
        remember: true,
        layout: 'vertical'
      }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <Form.Item
        label="Project name"
        name="name"
        rules={[
          {
            required: true,
            message: "Please input project name!",
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="Description"
        name="description"
        rules={[
          {
            required: false,
          },
        ]}
      >
        <TextArea/>
      </Form.Item>

      <Form.Item >
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
}
