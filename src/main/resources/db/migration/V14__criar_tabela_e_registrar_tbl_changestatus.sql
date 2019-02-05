
CREATE TABLE tbl_change_status (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

	cod_ticket BIGINT(20),

	cod_user_change BIGINT(20),

	date_change_status DATE,

	status VARCHAR(30),	
	

	FOREIGN KEY (cod_ticket) REFERENCES tbl_ticket(codigo),
	FOREIGN KEY (cod_user_change) REFERENCES tbl_user(codigo)


	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, cod_user_change, date_change_status, status ) values (2, 1, '2008-02-23' ,'New');
INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, cod_user_change, date_change_status, status ) values (3, 2, '2008-03-01' ,'Resolved');
INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, cod_user_change, date_change_status, status ) values (1, 3, '2008-05-31' ,'Approved');
