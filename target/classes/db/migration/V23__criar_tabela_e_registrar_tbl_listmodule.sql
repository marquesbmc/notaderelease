CREATE TABLE notadereleaseapi.tbl_listmodule (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    versao VARCHAR(20),
    observacao VARCHAR(30),
    codigo_releasenotes BIGINT(20),
    
    FOREIGN KEY (codigo_releasenotes) REFERENCES tbl_releasenotes(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('SINCO', '01.01.01.001','teste', 1);


INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('SINCO2', '01.01.01.001','teste', 1);


INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('DOIS', '02.02.02.002','teste', 2);

INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('DOIS 2', '02.02.02.002','teste', 2);

INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('TRES', '03.04.03.003','teste', 3);

INSERT INTO  notadereleaseapi.tbl_listmodule(nome, versao, observacao, codigo_releasenotes) 
values
('TRES e 1', '03.04.03.001','teste', 3);
