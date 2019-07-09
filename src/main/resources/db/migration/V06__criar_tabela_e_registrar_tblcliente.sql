CREATE TABLE tbl_cliente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_funcionario VARCHAR(50),
	matricula_funcionario VARCHAR(20),
	responsabilidade_funcionario VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO notadereleaseapi.tbl_cliente (nome_funcionario, matricula_funcionario, responsabilidade_funcionario ) values ('Bruno Marques','c086948','Consultor');

INSERT INTO notadereleaseapi.tbl_cliente (nome_funcionario, matricula_funcionario, responsabilidade_funcionario ) values ('Ronan','c086948','Desenvolvedor');


INSERT INTO notadereleaseapi.tbl_cliente (nome_funcionario, matricula_funcionario, responsabilidade_funcionario ) values ('Testador','c086948','Desenvolvedor');




