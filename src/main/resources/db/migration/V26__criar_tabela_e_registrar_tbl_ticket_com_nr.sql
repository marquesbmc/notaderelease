
CREATE TABLE tbl_ticket (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_usuario_cliente BIGINT(20),
	data_abertura DATETIME,	
        data_instalacao DATETIME ,	
	numero_nr BIGINT(20),
	coordenacao VARCHAR(30),
	status VARCHAR(30),
	codigo_usuario_aprovador BIGINT(20),
	info TEXT,
	descricao TEXT,
    
    	FOREIGN KEY (numero_nr) REFERENCES tbl_releasenotes(codigo),
	FOREIGN KEY (codigo_usuario_cliente) REFERENCES tbl_user(codigo),
	FOREIGN KEY (codigo_usuario_aprovador) REFERENCES tbl_user(codigo)

	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;




INSERT INTO  notadereleaseapi.tbl_ticket(codigo_usuario_cliente, data_abertura, data_instalacao, numero_nr,coordenacao, status, codigo_usuario_aprovador,info, descricao  ) 
values
(2,'2008-02-23','2008-02-23',3,'Coord1','Novo' ,null,'22','11111' );


INSERT INTO  notadereleaseapi.tbl_ticket(codigo_usuario_cliente, data_abertura, data_instalacao, numero_nr,coordenacao, status, codigo_usuario_aprovador,info, descricao  ) 
values
(3,'2008-02-23','2008-02-23',2,'Coord2','Aprovado' ,null,'22','11111' );
