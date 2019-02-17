CREATE TABLE tbl_user (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	matricula  VARCHAR(50),
	nome VARCHAR(50),
	password VARCHAR(150),
	profile VARCHAR(30),
	coordenacao VARCHAR(30),
	UNIQUE (matricula, nome)
	
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
	
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
			     ('c086948' , 'Bruno' , '123', 'ROLE_ADMIN', 'Arquitetura');
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('c086949' , 'Araujo Cavalcante' , '123', 'ROLE_CUSTOMER', 'SIRFG');		
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('c086777' , 'Edson Violino' , '123', 'ROLE_TECHNICIAN', 'SIFAG');		


