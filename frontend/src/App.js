import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// pages
import Home from "./pages/Home";
import NotFound from "./pages/NotFound";
import CreatePatient from "./pages/CreatePatient";
import RequireExam from "./pages/RequireExam";

// components
import NavBar from "./components/NavBar";

// styles
import "./styles/globals.scss";
import "./styles/fonts.scss";

function App() {
  localStorage.setItem("userId", "123");
  return (
    <>
      <BrowserRouter>
        <NavBar />
        <div className="bild-wrapper">
          <Switch>
            <Route path="/" exact={true} component={Home} />
            <Route
              path="/patient/create"
              exact={true}
              component={CreatePatient}
            />
            <Route path="/exam/create" exact={true} component={RequireExam} />
            <Route path="*" exact={true} component={NotFound} />
          </Switch>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
