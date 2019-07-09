CREATE TABLE tbl_releasenote (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_sistema VARCHAR(10),
	data_deploy DATE,
	tipo_deploy VARCHAR(30),
	ambiente_deploy VARCHAR(30),
	esta_aprovado BOOLEAN,
	versao_codigo_fonte VARCHAR(30),
	versao_codigo_compilado VARCHAR(30),
	versao_modelo_banco VARCHAR(30),
	escopo_notaderelease TEXT,
	funcionalidades_notaderelease TEXT,
	problemas_conhecidos_notaderelease TEXT,
    codigo_quality BIGINT(20),
    codigo_platform BIGINT(20),
    
    FOREIGN KEY (codigo_quality) REFERENCES tbl_quality(codigo),
	FOREIGN KEY (codigo_platform) REFERENCES tbl_platform(codigo)
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_releasenote(
	nome_sistema,
	data_deploy ,
	tipo_deploy,
	ambiente_deploy,
	esta_aprovado,
	versao_codigo_fonte,
	versao_codigo_compilado,
	versao_modelo_banco,
	escopo_notaderelease,
	funcionalidades_notaderelease,
	problemas_conhecidos_notaderelease,
    codigo_quality,
    codigo_platform) 
values 
	('SIRFG', '2008-02-23', 'Normal', 'Desenvolvimento', false, 'c201901011212', 'TAG', 'ver10.45','Escopo da release' ,'Funcionalidades', 'Problemas',1, 2);

INSERT INTO  notadereleaseapi.tbl_releasenote(
	nome_sistema,
	data_deploy ,
	tipo_deploy,
	ambiente_deploy,
	esta_aprovado,
	versao_codigo_fonte,
	versao_codigo_compilado,
	versao_modelo_banco,
	escopo_notaderelease,
	funcionalidades_notaderelease,
	problemas_conhecidos_notaderelease,
    codigo_quality,
    codigo_platform)  
values 
	('SIFUG', '2008-02-23', 'Hotdeploy', 'Homologação', true, 'c201901011212', 'TAG', 'ver10.45','Escopo da release' ,'Funcionalidades', 'Problemas',2, 1);
	
INSERT INTO  notadereleaseapi.tbl_releasenote(
	nome_sistema,
	data_deploy ,
	tipo_deploy,
	ambiente_deploy,
	esta_aprovado,
	versao_codigo_fonte,
	versao_codigo_compilado,
	versao_modelo_banco,
	escopo_notaderelease,
	funcionalidades_notaderelease,
	problemas_conhecidos_notaderelease,
    codigo_quality,
    codigo_platform)  
values 
	('SIRMO', '2008-02-23', 'Normal', 'Produção', false, 'c201901011212', 'TAG', 'ver10.45','Escopo da release' ,'Funcionalidades', 'Problemas',3, 3);
    

