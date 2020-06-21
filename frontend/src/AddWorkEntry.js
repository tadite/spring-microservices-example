import React from "react";
import "antd/dist/antd.css";
import "./index.css";
import { Form, DatePicker, TimePicker, Button } from "antd";

const { MonthPicker, RangePicker } = DatePicker;

export default function AddWorkEntry() {
  const onFinish = (fieldsValue) => {
    // Should format date value before submit.
    const rangeValue = fieldsValue["range-picker"];
    const rangeTimeValue = fieldsValue["range-time-picker"];
    const values = {
      ...fieldsValue,
      "date-picker": fieldsValue["date-picker"].format("YYYY-MM-DD"),
      "date-time-picker": fieldsValue["date-time-picker"].format(
        "YYYY-MM-DD HH:mm:ss"
      ),
      "month-picker": fieldsValue["month-picker"].format("YYYY-MM"),
      "range-picker": [
        rangeValue[0].format("YYYY-MM-DD"),
        rangeValue[1].format("YYYY-MM-DD"),
      ],
      "range-time-picker": [
        rangeTimeValue[0].format("YYYY-MM-DD HH:mm:ss"),
        rangeTimeValue[1].format("YYYY-MM-DD HH:mm:ss"),
      ],
      "time-picker": fieldsValue["time-picker"].format("HH:mm:ss"),
    };
    console.log("Received values of form: ", values);
  };

  return (
    <Form
      name="time_related_controls"
      initialValues={{
        remember: true,
        layout: "vertical",
      }}
      onFinish={onFinish}
    >
      <Form.Item name="date-time-picker" label="DatePicker[showTime]">
        <DatePicker showTime format="YYYY-MM-DD HH:mm:ss" />
      </Form.Item>
      <Form.Item name="time-picker" label="TimePicker">
        <TimePicker />
      </Form.Item>
      <Form.Item name="range-time-picker" label="RangePicker[showTime]">
        <RangePicker showTime format="HH:mm:ss" />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
}
