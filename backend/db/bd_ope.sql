CREATE DATABASE bd_clinica;

CREATE TABLE paciente (
    id_paciente int NOT NULL AUTO_INCREMENT,
    name_paciente varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    cpf char(11) NOT NULL,
    rg char(9) NOT NULL,
    phone char(11) NOT NULL,
    endereco varchar(255) NOT NULL,
    birth date NOT NULL,
    genero varchar(50) NOT NULL,
    PRIMARY KEY (id_paciente)
);

CREATE TABLE exame (
    id_exame int NOT NULL AUTO_INCREMENT,
    nome_exame varchar(255),
    PRIMARY KEY (id_exame)
);

CREATE TABLE agendamento (
    id_agendamento int NOT NULL AUTO_INCREMENT,
    data_agendamento date NOT NULL,
    id_exame int,
    id_paciente int,
    PRIMARY KEY (id_agendamento)
    FOREIGN KEY (id_exame) REFERENCES exame(id_exame)
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
);




