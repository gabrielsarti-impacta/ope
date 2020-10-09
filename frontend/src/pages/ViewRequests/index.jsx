import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
import cn from "classnames";
import sassVar from "./style.scss";

import Modal from "../../components/Modal";

import api from "../../services/api";

const { block } = sassVar;

const ViewRequests = () => {
  const [exams, setExams] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [fieldError, setFieldError] = useState(true);

  useEffect(() => {
    async function loadExamsMock() {
      // const response = await api.get("/");
      // setExams(response.data);

      // response.map((item) => {
      //   item.date = new Date(Date.parse(item.date));
      // });
      setExams([
        {
          id: "123",
          name: "Nome do exame",
          patient: "Sérgio Barros",
          date: new Date(),
        },
        {
          id: "123",
          name: "Nome do exame",
          patient: "Gabriel Sarti",
          date: new Date(),
        },
      ]);
    }

    async function loadExams() {
      const response = await api.get("/");
      setExams(response.data);

      response.map((item) => {
        item.date = new Date(Date.parse(item.date));
      });
    }

    loadExamsMock();
  }, []);

  const handleModalClose = () => {
    setModalOpen(false);
  };

  const rootCssClasses = cn(block);

  return (
    <>
      <div className={rootCssClasses}>
        <div className={`${block}__title`}>
          <div className={`${block}__wrapper`}>Solicitações</div>
        </div>
        <div className={`${block}__wrapper`}>
          <div className={`${block}__table`}>
            <div className={`${block}__table-header`}>
              <div className={`${block}__table-cell`}>Exame</div>
              <div className={`${block}__table-cell`}>Paciente</div>
              <div className={`${block}__table-cell`}>Data</div>
              <div className={`${block}__table-cell`}>Status</div>
            </div>
            {exams.map((exam) => {
              return (
                <div className={`${block}__table-row`}>
                  <div className={`${block}__table-cell`}>{exam.name}</div>
                  <div className={`${block}__table-cell`}>{exam.patient}</div>
                  <div
                    className={`${block}__table-cell`}
                  >{`${exam.date.getDate()}/${exam.date.getMonth()}/${exam.date.getFullYear()} - ${exam.date.getHours()}:${exam.date.getMinutes()}`}</div>
                  <div className={`${block}__table-cell`}></div>
                </div>
              );
            })}
          </div>
        </div>
      </div>
      {modalOpen && (
        <Modal message={modalMessage} closeModalHandler={handleModalClose} />
      )}
    </>
  );
};

ViewRequests.propTypes = {};

ViewRequests.defaultProps = {};

export { ViewRequests as default };
