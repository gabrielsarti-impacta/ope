import React from "react";
import { Link } from "react-router-dom";

import "./style.scss";

const Home = () => {
  return (
    <div className="home">
      <Link to="/patient/create">Criar paciente</Link>
    </div>
  );
};

export default Home;
