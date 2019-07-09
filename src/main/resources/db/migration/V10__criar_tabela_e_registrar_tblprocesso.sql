CREATE TABLE tbl_processo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_processo VARCHAR(50),
	tipo_processo VARCHAR(30)
	
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_processo (nome_processo, tipo_processo ) values ('FUGSP403112','StoreProcedure');

INSERT INTO tbl_processo (nome_processo, tipo_processo ) values ('FUGSP1233','bind');	

INSERT INTO tbl_processo (nome_processo, tipo_processo ) values ('FUGSP0001','Function');

INSERT INTO tbl_processo (nome_processo, tipo_processo ) values ('FUGSP3211','outros');

INSERT INTO tbl_processo (nome_processo, tipo_processo ) values ('FUGSP3224','JCL');
