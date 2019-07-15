
CREATE TABLE notadereleaseapi.tbl_listteam (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    matricula VARCHAR(20),
    papel VARCHAR(30),
    codigo_releasenotes BIGINT(20),
    
    FOREIGN KEY (codigo_releasenotes) REFERENCES tbl_releasenotes(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Bruno M', 'c01','Desenvolvedor', 1);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes)  
values
('Marcus', 'c03', 'Scrum master',1);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Caxias', 'c01','Desenvolvedor', 1);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Marcus', 'c03','Desenvolvedor' ,2);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Lauro', 'c03', 'Desenvolvedor',2);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Marcus', 'c03','Desenvolvedor', 1);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes)  
values
('Lauro', 'c03', 'Scrum master', 3);


INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Lauro', 'c03', 'Desenvolvedor',3);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes) 
values
('Marcus', 'c03','Desenvolvedor', 3);

INSERT INTO  notadereleaseapi.tbl_listteam(nome, matricula, papel, codigo_releasenotes)  
values
('Lauro', 'c03', 'Scrum master', 3);
    
