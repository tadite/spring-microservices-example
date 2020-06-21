import React from "react";
import { Switch, Route } from "react-router-dom";
import AddWorkEntry from "./AddWorkEntry";
import AddProject from "./AddProject";

export default function Router() {
  return (
    <Switch>
      <Route path="/projects">Project</Route>
      <Route path="/add-project">
        <AddProject />
      </Route>
      <Route path="/add-entry">
        <AddWorkEntry />
      </Route>
      <Route path="/statistics">Statistics</Route>
      <Route path="/notifications">Notifications</Route>
      <Route path="/" exact={true}>
        index
      </Route>
    </Switch>
  );
}
