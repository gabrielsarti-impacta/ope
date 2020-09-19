import React from "react";
import PropTypes from "prop-types";
import sassVar from "./style.scss";
import cn from "classnames";

const { block } = sassVar;

const Login = ({}) => {
  const rootCssClasses = cn(block);
  return <div className={rootCssClasses}></div>;
};

Login.propTypes = {};

Login.defaultProps = {};

export { Login as default };
