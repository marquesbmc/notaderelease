CREATE TABLE tbl_statusnotes (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nm_statusnotes VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Novo');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Atribuído');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Inconforme');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Reavaliar');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Aprovado');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Reprovado');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Cancelado');


