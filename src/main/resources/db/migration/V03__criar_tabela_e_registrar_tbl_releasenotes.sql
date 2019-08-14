CREATE TABLE tbl_database(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nm_modelobanco TEXT,
	nm_matrizacesso TEXT,
	nm_cargadados TEXT,
	nm_acessobd TEXT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

	



CREATE TABLE tbl_qualitycode(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	dt_consulta DATE, 
	nm_conceito VARCHAR(10),
	tt_linhas VARCHAR(10),
	tt_classes VARCHAR(10),
	tt_duplicacoes VARCHAR(10),
	tt_unitariocobertura VARCHAR(10),
	tt_unitarioignorado VARCHAR(10),
	tt_teste VARCHAR(10),
	tt_falhas VARCHAR(10),
	tt_erros VARCHAR(10),
	tt_problemablocker VARCHAR(10),
	tt_problemacritical VARCHAR(10),
	tt_problemamajor VARCHAR(10),
	tt_problemaminor VARCHAR(10),
	tt_problemainfo VARCHAR(10)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;








CREATE TABLE tbl_releasenotes(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nm_sistema VARCHAR(100),
	dt_criacao DATE, 
	tp_ambiente_deploy VARCHAR(30),
	nm_ambiente_deploy VARCHAR(30), 
	vs_codigo_fonte VARCHAR(30), 
	vs_codigo_compilado VARCHAR(30),
	nm_coordenador_ti VARCHAR(70), 
	nm_coordenador_projeto VARCHAR(70), 
	tp_status_nr VARCHAR(30),
	nm_resumo TEXT,
	nm_problemas TEXT,
	nm_funcionalidades TEXT,
	nm_infraestrutura_software TEXT,

        codigo_qualitycode BIGINT(20),
	codigo_database BIGINT(20),
    
    	FOREIGN KEY (codigo_qualitycode) REFERENCES tbl_qualitycode(codigo),
	FOREIGN KEY (codigo_database) REFERENCES tbl_database(codigo)
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;








