import React from "react";
import { Link } from "react-router-dom";

import "./style.scss";

const Header = () => {
  return (
    <div className="header">
      <Link to="/">Ir para a página inicial \o/</Link>
    </div>
  );
};

export default Header;
