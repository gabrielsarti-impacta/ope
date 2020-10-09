import React, { useState } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
import cn from "classnames";
import sassVar from "./style.scss";

import Modal from "../../components/Modal";

import api from '../../services/api';

const { block } = sassVar;

const CreatePatient = ({ }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [rg, setRg] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [birth, setBirth] = useState("");
  const [gender, setGender] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [fieldError, setFieldError] = useState(true);
  const [redirect, setRedirect] = useState(null);

  const handleFormSubmit = (e) => {
    e.preventDefault();


    if (
      name !== "" &&
      email !== "" &&
      cpf !== "" &&
      rg !== "" &&
      phone !== "" &&
      address !== "" &&
      birth !== "" &&
      gender !== ""
    ) {
      // Mock antes da conexão com o backend
      console.log({
        name,
        email,
        cpf,
        rg,
        phone,
        address,
        birth,
        gender,
      });
      setFieldError(false);
      setModalMessage("Paciente criado com sucesso");
      setModalOpen(true);
      // fim do mock
      // createPatient();
    } else {
      setFieldError(true);
      setModalMessage("Preencha todos os campos");
      setModalOpen(true);
    }
  };

  const createPatient = async () => {
    const response = await api.post('/', {
      name,
      email,
      cpf,
      rg,
      phone,
      address,
      birth,
      gender,
    });
    if (response.status === 200) {
      setFieldError(false);
      setModalMessage("Paciente criado com sucesso");
      setModalOpen(true);
    } else {
      setFieldError(true);
      setModalMessage("Erro ao criar o paciente");
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
          <div className={`${block}__wrapper`}>Criar paciente</div>
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
                  htmlFor="create-patient-name"
                >
                  Nome
                </label>
                <input
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-name"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-email"
                >
                  Email
                </label>
                <input
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-email"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-cpf"
                >
                  CPF
                </label>
                <input
                  value={cpf}
                  onChange={(e) => setCpf(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-cpf"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-rg"
                >
                  RG
                </label>
                <input
                  value={rg}
                  onChange={(e) => setRg(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-rg"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-phone"
                >
                  Telefone
                </label>
                <input
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-phone"
                  type="tel"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-address"
                >
                  Endereço
                </label>
                <input
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-address"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-birth"
                >
                  Data de nascimento
                </label>
                <input
                  value={birth}
                  onChange={(e) => setBirth(e.target.value)}
                  className={`${block}__input`}
                  type="text"
                  id="create-patient-birth"
                  type="date"
                />
              </div>
              <div className={`${block}__input-group`}>
                <label
                  className={`${block}__label`}
                  htmlFor="create-patient-gender"
                >
                  Sexo
                </label>
                <select
                  value={gender}
                  onChange={(e) => setGender(e.target.value)}
                  className={`${block}__input`}
                  id="create-patient-gender"
                >
                  <option></option>
                  <option>Masculino</option>
                  <option>Feminino</option>
                  <option>Não Binário</option>
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

CreatePatient.propTypes = {};

CreatePatient.defaultProps = {};

export { CreatePatient as default };
