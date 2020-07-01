import React from "react";
import {Route, Switch} from "react-router-dom";
import AddWorkEntry from "./AddWorkEntry";
import AddProject from "./AddProject";
import Projects from "./Projects";
import Project from "./Project";
import Task from "./Task";
import TimeEntries from "./TimeEntries";

export default function Router() {
    return (
        <Switch>
            <Route path="/projects/:projectId/tasks/:taskId/entries">
                <TimeEntries/>
            </Route>
            <Route path="/projects/:projectId/tasks/:taskId">
                <Task/>
            </Route>
            <Route path="/projects/:projectId">
                <Project/>
            </Route>
            <Route path="/projects">
                <Projects/>
            </Route>
            <Route path="/add-project">
                <AddProject/>
            </Route>
            <Route path="/add-entry">
                <AddWorkEntry/>
            </Route>
            <Route path="/statistics">Statistics</Route>
            <Route path="/notifications">Notifications</Route>
            <Route path="/" exact={true}>
                index
            </Route>
        </Switch>
    );
}
