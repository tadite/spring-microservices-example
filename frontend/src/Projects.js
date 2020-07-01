import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import "antd/dist/antd.css";
import "./index.css";
import {Button, Space, Table} from "antd";
import {fetchApi} from "./api/Api";
import ProjectForm from "./ProjectForm";

const columns = [
    {
        title: "Name",
        dataIndex: "name",
        key: "id",
        render: (text, record) => <Link to={"/projects/" + record.id}>{text}</Link>,
    },
    {
        title: "Description",
        dataIndex: "description",
        // render: (text) => text,
    }
];

export default function Projects() {
    const [hasError, setErrors] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState({});
    const [visible, setVisible] = useState(false);

    useEffect(() => {
        fetchApi("/api/projects/projects?size=5&page=" + (currentPage - 1))
            .then(res => res.json())
            .then(res => setData(res))
            .catch(res => setErrors(res))
    }, [currentPage]);

    return <div><Table columns={columns} dataSource={data.content}
                       pagination={{
                           current: currentPage,
                           pageSize: data.pageSize,
                           total: data.totalElements,
                           onChange: (page, pageSize) => setCurrentPage(page)
                       }}/>
        <Space>
            <Button type="primary" onClick={setVisible}>
                Add project
            </Button>
        </Space>
        <ProjectForm visible={visible}
                     onCreate={() => {
                         setVisible(false);
                     }}
                     onCancel={() => {
                         setVisible(false);
                     }}/>
    </div>;
}
