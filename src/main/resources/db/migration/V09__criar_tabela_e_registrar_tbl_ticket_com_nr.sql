
CREATE TABLE tbl_ticket (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_usuario_cliente BIGINT(20),
	data_abertura DATETIME,	
        data_instalacao DATETIME ,	
	numero_nr BIGINT(20),
	coordenacao VARCHAR(100),
	status VARCHAR(30),
	codigo_usuario_aprovador BIGINT(20),
	info TEXT,
	descricao TEXT,
    
    	FOREIGN KEY (numero_nr) REFERENCES tbl_releasenotes(codigo),
	FOREIGN KEY (codigo_usuario_cliente) REFERENCES tbl_user(codigo),
	FOREIGN KEY (codigo_usuario_aprovador) REFERENCES tbl_user(codigo)

	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;



