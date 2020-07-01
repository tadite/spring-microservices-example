import React, {useEffect, useState} from "react";
import "antd/dist/antd.css";
import "./index.css";
import {fetchApi} from "./api/Api";
import {DatePicker, Form, Input, Modal} from 'antd';
import moment from 'moment';

const {MonthPicker, RangePicker} = DatePicker;

export default function TimeEntryForm({taskId, projectId, visible, onCreate, onCancel}) {
    const [form] = Form.useForm();

    const [hasError, setErrors] = useState(false);
    const [projectData, setProjectData] = useState({});

    useEffect(() => {
        form.setFieldsValue({
            projectName: projectData.name,
        });
    }, [projectData]);

    useEffect(() => {
        fetchApi("/api/projects/projects/" + projectId)
            .then(res => res.json())
            .then(res => setProjectData(res))
            .catch(res => setErrors(res));
    }, [form]);

    function onOk() {
        form
            .validateFields()
            .then(values => {
                fetchApi("/api/tracker/records", {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        taskId,
                        startTime: values.workTimeRange[0].toISOString(),
                        endTime: values.workTimeRange[1].toISOString(),
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
        title="Add work entry"
        okText="Add"
        cancelText="Cancel"
        onCancel={onCancel}
        onOk={onOk}
    >
        <Form
            name="basic"
            form={form}
            initialValues={{
                ["workTimeRange"]: [moment(), moment()]
            }}
        >
            <Form.Item
                label="Project"
                name="projectName"
                rules={[
                    {
                        message: 'Please select project!',
                    },
                ]}>
                <Input disabled={true}/>
            </Form.Item>
            <Form.Item name="workTimeRange" label="Work time">
                <RangePicker showTime format="YYYY-MM-DD HH:mm:ss"/>
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
