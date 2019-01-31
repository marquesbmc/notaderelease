CREATE TABLE tbl_rel_notaxmodulo (
	codigo_notaderelease BIGINT(20) NOT NULL,
	codigo_modulo BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_notaderelease, codigo_modulo),

	FOREIGN KEY (codigo_notaderelease) REFERENCES tbl_notaderelease(codigo),
	FOREIGN KEY (codigo_modulo) REFERENCES tbl_modulo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO tbl_rel_notaxmodulo (codigo_notaderelease, codigo_modulo) values (1, 1);
INSERT INTO tbl_rel_notaxmodulo (codigo_notaderelease, codigo_modulo) values (1, 2);
INSERT INTO tbl_rel_notaxmodulo (codigo_notaderelease, codigo_modulo) values (2, 3);
INSERT INTO tbl_rel_notaxmodulo (codigo_notaderelease, codigo_modulo) values (3, 1);
INSERT INTO tbl_rel_notaxmodulo (codigo_notaderelease, codigo_modulo) values (3, 2);

