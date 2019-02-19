
CREATE TABLE notadereleaseapi.tbl_module (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_modulo VARCHAR(30),
    versao_modulo VARCHAR(30),
    codigo_releasenote BIGINT(20),
    
    FOREIGN KEY (codigo_releasenote) REFERENCES tbl_releasenote(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
    
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('util', '01', 1);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('template', '02', 1);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('security', '03', 1);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('util', '01', 2);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('template', '02', 2);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('security', '03', 2);
INSERT INTO  notadereleaseapi.tbl_module( nome_modulo, versao_modulo, codigo_releasenote) 
values
('security', '03', 3);
    