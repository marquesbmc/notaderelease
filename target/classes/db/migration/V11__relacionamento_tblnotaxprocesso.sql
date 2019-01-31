CREATE TABLE tbl_rel_notaxprocesso (
	codigo_notaderelease BIGINT(20) NOT NULL,
	codigo_processo BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_notaderelease, codigo_processo),

	FOREIGN KEY (codigo_notaderelease) REFERENCES tbl_notaderelease(codigo),
	FOREIGN KEY (codigo_processo) REFERENCES tbl_processo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (1, 1);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (1, 2);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (2, 3);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (3, 1);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (3, 2);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (2, 4);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (2, 1);
INSERT INTO tbl_rel_notaxprocesso (codigo_notaderelease, codigo_processo) values (1, 4);
