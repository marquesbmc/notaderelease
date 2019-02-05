CREATE TABLE tbl_user (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	matricula  VARCHAR(7),
	nome VARCHAR(50),
	password VARCHAR(10),
	profile VARCHAR(30),
	coordenacao VARCHAR(30),
	UNIQUE (matricula, nome)
	
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
	
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('c086948' , 'Bruno Marques' , 'cxxxx', 'ROLE_ADMIN', 'Arquitetura');
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('c086949' , 'Araujo Cavalcante' , 'caaaaa', 'ROLE_CUSTOMER', 'SIRFG');		
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('c086777' , 'Edson Violino' , 'ccccaa', 'ROLE_TECHNICIAN', 'SIFAG');		


