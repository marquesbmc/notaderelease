
CREATE TABLE tbl_change_status (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

	cod_ticket BIGINT(20),

	nome_user_change VARCHAR(50),

	date_change_status DATE,

	status_ticket VARCHAR(30),	
	

	FOREIGN KEY (cod_ticket) REFERENCES tbl_ticket(codigo)


	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, nome_user_change, date_change_status, status_ticket ) values (2, 'Bruno', '2008-02-23' ,'New');
INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, nome_user_change, date_change_status, status_ticket ) values (3, 'Bruno', '2008-03-01' ,'Resolved');
INSERT INTO  notadereleaseapi.tbl_change_status (cod_ticket, nome_user_change, date_change_status, status_ticket ) values (1, 'Bruno', '2008-05-31' ,'Approved');
