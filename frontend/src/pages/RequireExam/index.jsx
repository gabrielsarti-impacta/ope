import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
import cn from "classnames";
import sassVar from "./style.scss";

import Modal from "../../components/Modal";

import api from "../../services/api";

const { block } = sassVar;

const RequireExam = () => {
  const [date, setDate] = useState("");
  const [examId, setExamId] = useState("");
  const [patientId, setPatientId] = useState("");
  const [exams, setExams] = useState([]);
  const [patients, setPatients] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [fieldError, setFieldError] = useState(true);
  const [redirect, setRedirect] = useState(null);

  useEffect(() => {
    async function loadExams() {
      const response = await api.get("/exam");
      setExams(response.data);
    }

    async function loadPatients() {
      const response = await api.get("/patient");
      setPatients(response.data);
    }

    loadExams();
    loadPatients();
  }, []);

  const minDate = new Date();
  var weekInMilliseconds = 7 * 24 * 60 * 60 * 1000;
  minDate.setTime(minDate.getTime() + weekInMilliseconds);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    const userId = localStorage.getItem("userId");

    if (date !== "" && examId !== "" && patientId !== "") {
      console.log({
        date,
        examId,
        patientId,
        userId,
      });
      setFieldError(false);
      setModalMessage("Exame solicitado com sucesso");
      setModalOpen(true);
    } else {
      setFieldError(true);
      setModalMessage("Preencha todos os campos");
      setModalOpen(true);
    }
  };

  const handleModalClose = () => {
    setModalOpen(false);
    if (!fieldError) {
      setRedirect("/");
    }
  };

  const rootCssClasses = cn(block);

  if (redirect) {
    return <Redirect to={redirect} />;
  }
  return (
    <>
      <div className={rootCssClasses}>
        <div className={`${block}__title`}>
          <div className={`${block}__wrapper`}>Solicitar Exame</div>
        </div>
        <div className={`${block}__wrapper`}>
          <form
            className={`${block}__form`}
            method="post"
            onSubmit={handleFormSubmit}
          >
            <div className={`${block}__form-inner`}>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="require-exam-date"
                >
                  Selecione uma data
                </label>
                <input
                  value={date}
                  onChange={(e) => setDate(e.target.value)}
                  className={`${block}__input`}
                  type="datetime-local"
                  id="require-exam-date"
                  min={`${minDate.toISOString().substring(0, 10)}T00:00:00`}
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="require-exam-name"
                >
                  Exame
                </label>
                <select
                  value={examId}
                  onChange={(e) => setExamId(e.target.value)}
                  className={`${block}__input`}
                  id="require-exam-name"
                >
                  {exams.map((exam) => {
                    return <option>{exam}</option>;
                  })}
                </select>
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="require-exam-patient"
                >
                  Paciente
                </label>
                <select
                  value={patientId}
                  onChange={(e) => setPatientId(e.target.value)}
                  className={`${block}__input`}
                  id="require-exam-patient"
                >
                  {patients.map((patient) => {
                    return <option>{patient}</option>;
                  })}
                </select>
              </div>
            </div>
            <button className={`${block}__button`} type="submit">
              Enviar
            </button>
          </form>
        </div>
      </div>
      {modalOpen && (
        <Modal message={modalMessage} closeModalHandler={handleModalClose} />
      )}
    </>
  );
};

RequireExam.propTypes = {};

RequireExam.defaultProps = {};

export { RequireExam as default };
