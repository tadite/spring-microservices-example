import React, {useEffect, useState} from "react";
import {useHistory, useParams} from "react-router-dom";
import "antd/dist/antd.css";
import "./index.css";
import {fetchApi} from "./api/Api";
import {Button, Descriptions, Space} from 'antd';
import TimeEntryForm from "./TimeEntryForm";


export default function Task() {
    let history = useHistory();
    let {taskId, projectId} = useParams();
    const [hasError, setErrors] = useState(false);
    const [data, setData] = useState({});
    const [visible, setVisible] = useState(false);

    function fetchTaskInfo() {
        fetchApi("/api/projects/tasks/" + taskId)
            .then(res => res.json())
            .then(res => setData(res))
            .catch(res => setErrors(res))
    }

    useEffect(() => {
        fetchTaskInfo();
    }, []);

    function routeToEntries() {
        history.push("/projects/" + projectId + "/tasks/" + taskId + "/entries");
    }

    function onTaskCreated() {
        setVisible(false);
    }

    return <div>
        <Descriptions title="Task Info">
            <Descriptions.Item label="Name">{data.name}</Descriptions.Item>
            <Descriptions.Item label="Description">{data.description}</Descriptions.Item>
            <Descriptions.Item
                label="Created time">{new Date(Date.parse(data.createdAt)).toLocaleString()}</Descriptions.Item>
        </Descriptions>
        <Space>
            <Button type="primary" onClick={setVisible}>
                Add time entry
            </Button>
            <Button type="primary" onClick={routeToEntries}>
                Show work entries
            </Button>
        </Space>
        <TimeEntryForm taskId={taskId}
                       projectId={projectId}
                       visible={visible}
                       onCreate={onTaskCreated}
                       onCancel={() => {
                           setVisible(false);
                       }}/>
    </div>
        ;
}
