import React from "react";
import PropTypes from "prop-types";
import sassVar from "./style.scss";
import cn from "classnames";

const { block } = sassVar;

const Modal = ({ className, message, theme, closeModalHandler }) => {
  const rootCssClasses = cn(block, className);

  const handleCloseModal = () => {
    closeModalHandler();
  };

  return (
    <div className={rootCssClasses}>
      <div className={`${block}__wrapper`}>
        <span className={`${block}__close`} onClick={handleCloseModal}>
          &#10005;
        </span>
        <span className={`${block}__message`}>{message}</span>
      </div>
    </div>
  );
};

export default Modal;
