
CREATE TABLE tbl_ticket (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_usuario_cliente BIGINT(20),
	data_abertura DATE,	
    data_instalacao DATE,	
	numero_nr BIGINT(20),
	status_notaderelease VARCHAR(30),
	codigo_usuario_aprovador BIGINT(20),
	descricao TEXT,
    
    
	FOREIGN KEY (codigo_usuario_cliente) REFERENCES tbl_user(codigo),
	FOREIGN KEY (codigo_usuario_aprovador) REFERENCES tbl_user(codigo)

	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

	INSERT INTO tbl_ticket (codigo_usuario_cliente, data_abertura, data_instalacao, numero_nr, status_notaderelease, codigo_usuario_aprovador, descricao ) values (1 , '2008-11-01' , null , 123, 'Resolved', 2,'Qualquer coisa' );

	INSERT INTO tbl_ticket (codigo_usuario_cliente, data_abertura, data_instalacao, numero_nr, status_notaderelease, codigo_usuario_aprovador, descricao ) values (2 , '2008-09-10' , null , 22, 'Approved', 3,'Qualquer coisa' );

	INSERT INTO tbl_ticket (codigo_usuario_cliente, data_abertura, data_instalacao, numero_nr, status_notaderelease, codigo_usuario_aprovador, descricao ) values (3, '2008-02-23', '2009-03-01',120, 'Closed', 1,'Qualquer');






