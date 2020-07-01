import React, {useState} from "react";
import "antd/dist/antd.css";
import "./index.css";
import {fetchApi} from "./api/Api";
import {Form, Input, Modal} from 'antd';

export default function ProjectForm({visible, onCreate, onCancel}) {
    const [form] = Form.useForm();

    const [hasError, setErrors] = useState(false);

    function onOk() {
        form
            .validateFields()
            .then(values => {
                fetchApi("/api/projects/projects", {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: values.name,
                        description: values.description
                    })
                })
                    .then(res => res.json())
                    .then(res => onCreate())
                    .catch(res => setErrors(res));
            });
    }

    return <Modal
        visible={visible}
        title="Add project"
        okText="Add"
        cancelText="Cancel"
        onCancel={onCancel}
        onOk={onOk}
    >
        <Form
            name="basic"
            form={form}
        >
            <Form.Item
                label="Name"
                name="name"
                rules={[
                    {
                        message: 'Please select name!',
                        required: true
                    },
                ]}>
                <Input/>
            </Form.Item>
            <Form.Item
                label="Description"
                name="description"
                rules={[
                    {
                        required: false
                    },
                ]}>
                <Input/>
            </Form.Item>
        </Form>
    </Modal>
        ;
}
