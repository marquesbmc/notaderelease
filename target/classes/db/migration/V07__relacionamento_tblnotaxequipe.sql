
CREATE TABLE tbl_rel_notaxcliente (
	codigo_notaderelease BIGINT(20) NOT NULL,
	codigo_cliente BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_notaderelease, codigo_cliente),

	FOREIGN KEY (codigo_notaderelease) REFERENCES tbl_notaderelease(codigo),
	FOREIGN KEY (codigo_cliente) REFERENCES tbl_cliente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_rel_notaxcliente (codigo_notaderelease, codigo_cliente) values (1, 1);
INSERT INTO tbl_rel_notaxcliente (codigo_notaderelease, codigo_cliente) values (1, 2);
INSERT INTO tbl_rel_notaxcliente (codigo_notaderelease, codigo_cliente) values (2, 3);
