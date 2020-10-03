import React from "react";
import sassVar from "./style.scss";
import cn from "classnames";
import { Link } from "react-router-dom";
import NavBar from "../../components/NavBar";

import BildIcon from "../../assets/bild-icon.png";

const { block } = sassVar;

const Home = () => {
  const rootCssClasses = cn(block);

  return (
    <div className={rootCssClasses}>
      <div className={`${block}__icon-block`}>
        <img className={`${block}__icon`} src={BildIcon} alt="" />
      </div>
    </div>
  );
};

export default Home;
