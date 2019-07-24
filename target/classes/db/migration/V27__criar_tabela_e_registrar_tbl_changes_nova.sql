
CREATE TABLE tbl_change_status (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

	cod_ticket BIGINT(20),

	nome_user_change VARCHAR(50),

	date_change_status DATETIME,

	status_ticket VARCHAR(30),	
	
    informacao TEXT,	

	FOREIGN KEY (cod_ticket) REFERENCES tbl_ticket(codigo)


	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

