import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import "antd/dist/antd.css";
import "./index.css";
import {Table} from "antd";
import {fetchApi} from "./api/Api";

const columns = [
    {
        title: "Start time",
        dataIndex: "startTime",
    },
    {
        title: "End time",
        dataIndex: "endTime",
    },
    {
        title: "Description",
        dataIndex: "description",
    }
];

export default function TimeEntries() {
    let {taskId} = useParams();
    const [hasError, setErrors] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [data, setData] = useState({});

    useEffect(() => {
        fetchApi("/api/tracker/records?taskId=" + taskId + "&size=5&page=" + (currentPage-1))
            .then(res => res.json())
            .then(res => setData(res))
            .catch(res => setErrors(res))
    }, [currentPage]);

    return <Table columns={columns} dataSource={data.content}
                  pagination={{
                      current: currentPage,
                      pageSize: 5,
                      total: data.totalElements,
                      onChange: (page, pageSize) => setCurrentPage(page)
                  }}/>;
}
