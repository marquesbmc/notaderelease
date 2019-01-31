CREATE TABLE tbl_modulo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_modulo VARCHAR(50),
	versao_modulo VARCHAR(30)
	
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_modulo (nome_modulo, versao_modulo ) values ('sifug_template','01.00.002');

INSERT INTO tbl_modulo (nome_modulo, versao_modulo ) values ('sifug','10.00.002');	

INSERT INTO tbl_modulo (nome_modulo, versao_modulo ) values ('sifug_sisgr','00.00.001');

INSERT INTO tbl_modulo (nome_modulo, versao_modulo ) values ('sifug_relatorio','00.99.001');

