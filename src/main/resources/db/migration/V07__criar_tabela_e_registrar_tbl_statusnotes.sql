CREATE TABLE tbl_statusnotes (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nm_statusnotes VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Submeter');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Atribuído');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Aprovado');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Reprovado');

INSERT INTO  notadereleaseapi.tbl_statusnotes(nm_statusnotes) 
values
('Inconforme');


