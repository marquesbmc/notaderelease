
CREATE TABLE notadereleaseapi.tbl_team (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
    matricula VARCHAR(20),
    funcao VARCHAR(30),
    codigo_releasenote BIGINT(20),
    
    FOREIGN KEY (codigo_releasenote) REFERENCES tbl_releasenote(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
    
INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote) 
values
('Bruno M', 'c01','Desenvolvedor', 1);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote)  
values
('Marcus', 'c03', 'Scrum master',1);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote) 
values
('Caxias', 'c01','Desenvolvedor', 1);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote) 
values
('Marcus', 'c03','Desenvolvedor' ,2);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote) 
values
('Lauro', 'c03', 'Desenvolvedor',2);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote) 
values
('Marcus', 'c03','Desenvolvedor', 3);

INSERT INTO  notadereleaseapi.tbl_team(nome, matricula, funcao, codigo_releasenote)  
values
('Lauro', 'c03', 'Scrum master', 1);
