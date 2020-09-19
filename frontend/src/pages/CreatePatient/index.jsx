import React from "react";
import PropTypes from "prop-types";
import sassVar from "./style.scss";
import cn from "classnames";

const { block } = sassVar;

const CreatePatient = ({}) => {
  const rootCssClasses = cn(block);
  return <div className={rootCssClasses}>patient</div>;
};

CreatePatient.propTypes = {};

CreatePatient.defaultProps = {};

export { CreatePatient as default };
