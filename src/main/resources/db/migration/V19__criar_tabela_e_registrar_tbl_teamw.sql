
CREATE TABLE notadereleaseapi.tbl_team (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
    matricula VARCHAR(20),
    funcao VARCHAR(30),
    codigo_releasenote BIGINT(20),
    
    FOREIGN KEY (codigo_releasenote) REFERENCES tbl_releasenote(codigo)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
    



