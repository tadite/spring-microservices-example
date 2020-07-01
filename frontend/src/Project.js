import React, {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import "antd/dist/antd.css";
import "./index.css";
import {fetchApi} from "./api/Api";
import {Button, Descriptions, Space, Table} from 'antd';
import TaskForm from "./TaskForm";


export default function Project() {
    let {projectId} = useParams();
    const [hasError, setErrors] = useState(false);
    const [data, setData] = useState({});
    const [tasks, setTasks] = useState({});
    const [currentPage, setCurrentPage] = useState(1);
    const [visible, setVisible] = useState(false);

    const columns = [
        {
            title: "Name",
            dataIndex: "name",
            key: "id",
            render: (text, record) => <Link to={"/projects/" + projectId + "/tasks/" + record.id}>{text}</Link>,
        },
        {
            title: "Description",
            dataIndex: "description",
            // render: (text) => text,
        }
    ];

    useEffect(() => {
        fetchApi("/api/projects/projects/" + projectId)
            .then(res => res.json())
            .then(res => setData(res))
            .catch(res => setErrors(res))
    }, []);

    function fetchProjectsPage() {
        fetchApi("/api/projects/projects/" + projectId + "/tasks?size=5&page=" + (currentPage - 1))
            .then(res => res.json())
            .then(res => setTasks(res))
            .catch(res => setErrors(res))
    }

    useEffect(() => {
        fetchProjectsPage();
    }, []);

    function onTaskCreate() {
        fetchProjectsPage();
        setVisible(false);
    }

    return <div>
        <Descriptions title="Project Info">
            <Descriptions.Item label="Name">{data.name}</Descriptions.Item>
            <Descriptions.Item label="Description">{data.description}</Descriptions.Item>
            <Descriptions.Item
                label="Created time">{new Date(Date.parse(data.createdAt)).toLocaleString()}</Descriptions.Item>
        </Descriptions>
        <Table columns={columns} dataSource={tasks.content}
               pagination={{
                   current: currentPage,
                   pageSize: tasks.pageSize,
                   total: tasks.totalElements,
                   onChange: (page, pageSize) => setCurrentPage(page)
               }}/>
        <Space>
            <Button type="primary" onClick={setVisible}>
                Add task
            </Button>
        </Space>
        <TaskForm projectId={projectId}
                  visible={visible}
                  onCreate={onTaskCreate}
                  onCancel={() => {
                      setVisible(false);
                  }}/>
    </div>
        ;
}
