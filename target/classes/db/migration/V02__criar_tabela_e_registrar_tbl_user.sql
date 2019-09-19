CREATE TABLE tbl_user (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	matricula  VARCHAR(50),
	nome VARCHAR(50),
	password VARCHAR(150),
	profile VARCHAR(30),
	coordenacao VARCHAR(100),
	UNIQUE (matricula, nome)
	
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
	
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
			     ('A086948' , 'Bruno Marques Costa' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_ADMIN', 'Arquitetura de Sistemas e Padronização');
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('C1' , 'Teste para SIDFG' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_CUSTOMER', 	'Depósito e Transferência de Recursos');	
INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('C2' , 'Teste para SIDFG2' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_CUSTOMER', 	'Depósito e Transferência de Recursos');		
		
	INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
		('T086948' , 'Bruno Marques Costa' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_TECHNICIAN', 		'Arquitetura de Sistemas e Padronização');	


INSERT INTO tbl_user (matricula, nome, password, profile, coordenacao ) values 
			     ('C086948' , 'Bruno Marques Costa' , '$2a$10$O0NBXCVx38/3hMWs5E4H.uKu.A1bZq2xx4ll937Dk37RjRChKUTnS', 'ROLE_CUSTOMER', 'Arquitetura de Sistemas e Padronização');	


