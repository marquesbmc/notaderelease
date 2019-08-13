
CREATE TABLE notadereleaseapi.tbl_listteam (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    matricula VARCHAR(20),
    papel VARCHAR(30),
    codigo_releasenotes BIGINT(20),
    
    FOREIGN KEY (codigo_releasenotes) REFERENCES tbl_releasenotes(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;



    
