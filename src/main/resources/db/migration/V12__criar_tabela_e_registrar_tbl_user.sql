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
			     ('cA' , 'Bruno' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_ADMIN', 'Arquitetura');
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('cC' , 'Araujo Cavalcante' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_CUSTOMER', 		'Coord1');	

	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('cC2' , 'Araujo Cavalcante2' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_CUSTOMER', 		'Coord2');	
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('cT' , 'Edson Violino' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_TECHNICIAN', 		'Arquitetura');		


