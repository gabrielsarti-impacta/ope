import React from "react";
import sassVar from "./style.scss";
import { Link } from "react-router-dom";
import cn from "classnames";

import { ReactComponent as AddUserIcon } from "../../assets/add-user.svg";
import { ReactComponent as RequireExamIcon } from "../../assets/add-document.svg";
import { ReactComponent as ViewRequestsIcon } from "../../assets/document.svg";
import BildIcon from "../../assets/bild.png";

const { block } = sassVar;

const NavBar = ({}) => {
  const rootCssClasses = cn(block);

  return (
    <div className={rootCssClasses}>
      <ul className={`${block}__list`}>
        <li className={`${block}__item`}>
          <Link to="/" className={`${block}__link`}>
            <img
              className={`${block}__icon-main`}
              src={BildIcon}
              alt="bild diagnósticos"
            />
          </Link>
        </li>
        <li title="Criar paciente" className={`${block}__item`}>
          <Link to="/patient/create" className={`${block}__link`}>
            <AddUserIcon className={`${block}__icon`} />
          </Link>
        </li>
        <li title="Solicitar Exame" className={`${block}__item`}>
          <Link to="/exam/create" className={`${block}__link`}>
            <RequireExamIcon className={`${block}__icon`} />
          </Link>
        </li>
        <li title="Visualizar Solicitações" className={`${block}__item`}>
          <Link to="/exam" className={`${block}__link`}>
            <ViewRequestsIcon className={`${block}__icon`} />
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default NavBar;
