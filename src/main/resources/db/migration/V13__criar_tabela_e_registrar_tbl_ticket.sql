
CREATE TABLE tbl_ticket (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_usuario_cliente BIGINT(20),
	data_abertura DATETIME,	
    data_instalacao DATETIME ,	
	numero_nr BIGINT(20),
	status VARCHAR(30),
	codigo_usuario_aprovador BIGINT(20),
	info TEXT,
	descricao TEXT,
    
    
	FOREIGN KEY (codigo_usuario_cliente) REFERENCES tbl_user(codigo),
	FOREIGN KEY (codigo_usuario_aprovador) REFERENCES tbl_user(codigo)

	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

	