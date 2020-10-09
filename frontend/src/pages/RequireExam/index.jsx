import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
import cn from "classnames";
import sassVar from "./style.scss";

import Modal from "../../components/Modal";

import api from "../../services/api";

const { block } = sassVar;

const RequireExam = () => {
  const [userId, setUserId] = useState("");
  const [date, setDate] = useState("");
  const [examId, setExamId] = useState("");
  const [patientId, setPatientId] = useState("");
  const [exams, setExams] = useState([]);
  const [patients, setPatients] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [fieldError, setFieldError] = useState(true);
  const [redirect, setRedirect] = useState(null);

  // Mock antes da conexão com o backend
  const examsResponse = {
    data: [
      {
        id: "1",
        name: "Exame 1",
      },
      {
        id: "2",
        name: "Exame 2",
      },
      {
        id: "3",
        name: "Exame 3",
      }
    ],
  };
  const patientsResponse = {
    data: [
      {
        id: "1",
        name: "Paciente 1",
      },
      {
        id: "2",
        name: "Paciente 2",
      },
      {
        id: "3",
        name: "Paciente 3",
      }
    ],
  };
  useEffect(() => {
    setUserId(localStorage.getItem("userId"));

    async function loadExams() {
      const response = examsResponse;
      setExams(response.data);
    }

    async function loadPatients() {
      const response = patientsResponse;
      setPatients(response.data);
    }

    loadExams();
    loadPatients();
  }, []);

  // fim do mock

  /** 
    * Conexão com o backend
    * 
    
      useEffect(() => {
        async function loadExamsAndPatients() {
          const response = await api.get('/');
          setExams(response.data.exams);
          setPatients(response.data.patients);
        }
        loadExamsAndPatients();
      }, []);
    */
  const minDate = new Date();
  var weekInMilliseconds = 7 * 24 * 60 * 60 * 1000;
  minDate.setTime(minDate.getTime() + weekInMilliseconds);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    if (date !== "" && examId !== "" && patientId !== "") {
      let newDateArray = 0;
      newDateArray = date.split("T");
      newDateArray[1] = newDateArray[1] + ":00";
      const newDate = newDateArray.join(' ');
      setDate(newDate);
      // Mock antes da conexão com o backend
      console.log({
        newDate,
        examId,
        patientId,
        userId,
      });
      setFieldError(false);
      setModalMessage("Exame solicitado com sucesso");
      setModalOpen(true);
      // fim do mock
      // createNewExam();
    } else {
      setFieldError(true);
      setModalMessage("Preencha todos os campos");
      setModalOpen(true);
    }
  };

  const createNewExam = async () => {
    const response = await api.post('/', {
      date,
      examId,
      patientId,
      userId,
    });
    if (response.status === 200) {
      setFieldError(false);
      setModalMessage("Exame solicitado com sucesso");
      setModalOpen(true);
    } else {
      setFieldError(true);
      setModalMessage("Erro ao solicitar o exame");
      setModalOpen(true);
    }
  }

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
                  <option></option>
                  {exams.map((exam) => {
                    return <option id={`patient-dropdown--new-exam--${exam.id}`}>{exam.name}</option>;
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
                  <option></option>
                  {patients.map((patient) => {
                    return <option id={`patient-dropdown--new-exam--${patient.id}`}>{patient.name}</option>;
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
