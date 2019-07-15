CREATE TABLE tbl_database(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nm_modelobanco TEXT,
	nm_matrizacesso TEXT,
	nm_cargadados TEXT,
	nm_acessobd TEXT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

	
INSERT INTO  notadereleaseapi.tbl_database(
	nm_modelobanco,
	nm_matrizacesso,
	nm_cargadados,
	nm_acessobd) 
values
	('blabla1','blabla1','blabla1','blabla1');


INSERT INTO  notadereleaseapi.tbl_database(
	nm_modelobanco,
	nm_matrizacesso,
	nm_cargadados,
	nm_acessobd) 
values
	('blabla2','blabla2','blabla2','blabla2');

INSERT INTO  notadereleaseapi.tbl_database(
	nm_modelobanco,
	nm_matrizacesso,
	nm_cargadados,
	nm_acessobd) 
values
	('blabla3','blabla3','blabla3','blabla3');



CREATE TABLE tbl_qualitycode(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	dt_consulta DATE, 
	nm_conceito VARCHAR(10),
	tt_linhas VARCHAR(10),
	tt_classes VARCHAR(10),
	tt_duplicacoes VARCHAR(10),
	tt_unitariocobertura VARCHAR(10),
	tt_unitarioignorado VARCHAR(10),
	tt_teste VARCHAR(10),
	tt_falhas VARCHAR(10),
	tt_erros VARCHAR(10),
	tt_problemablocker VARCHAR(10),
	tt_problemacritical VARCHAR(10),
	tt_problemamajor VARCHAR(10),
	tt_problemaminor VARCHAR(10),
	tt_problemainfo VARCHAR(10)

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  notadereleaseapi.tbl_qualitycode(
	dt_consulta, 
	nm_conceito,
	tt_linhas,
	tt_classes,
	tt_duplicacoes,
	tt_unitariocobertura,
	tt_unitarioignorado,
	tt_teste,
	tt_falhas,
	tt_erros,
	tt_problemablocker,
	tt_problemacritical,
	tt_problemamajor,
	tt_problemaminor,
	tt_problemainfo) 
values
	('2008-02-23',',A','13','13','13','13','13','13','13','13','13','13','13','13','13');

INSERT INTO  notadereleaseapi.tbl_qualitycode(
	dt_consulta, 
	nm_conceito,
	tt_linhas,
	tt_classes,
	tt_duplicacoes,
	tt_unitariocobertura,
	tt_unitarioignorado,
	tt_teste,
	tt_falhas,
	tt_erros,
	tt_problemablocker,
	tt_problemacritical,
	tt_problemamajor,
	tt_problemaminor,
	tt_problemainfo) 
values
	('2008-02-23',',A','14','14','14','14','14','14','14','14','14','14','14','14','14');

INSERT INTO  notadereleaseapi.tbl_qualitycode(
	dt_consulta, 
	nm_conceito,
	tt_linhas,
	tt_classes,
	tt_duplicacoes,
	tt_unitariocobertura,
	tt_unitarioignorado,
	tt_teste,
	tt_falhas,
	tt_erros,
	tt_problemablocker,
	tt_problemacritical,
	tt_problemamajor,
	tt_problemaminor,
	tt_problemainfo) 
values
	('2008-02-23',',A','15','15','15','15','15','15','15','15','15','15','15','15','15');





CREATE TABLE tbl_releasenotes(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nm_sistema VARCHAR(30),
	dt_criacao DATE, 
	tp_ambiente_deploy VARCHAR(30),
	nm_ambiente_deploy VARCHAR(30), 
	vs_codigo_fonte VARCHAR(30), 
	vs_codigo_compilado VARCHAR(30),
	nm_coordenador_ti VARCHAR(30), 
	nm_coordenador_projeto VARCHAR(50), 
	tp_status_nr VARCHAR(30),
	nm_resumo TEXT,
	nm_problemas TEXT,
	nm_funcionalidades TEXT,
	nm_infraestrutura_software TEXT,

        codigo_qualitycode BIGINT(20),
	codigo_database BIGINT(20),
    
    	FOREIGN KEY (codigo_qualitycode) REFERENCES tbl_qualitycode(codigo),
	FOREIGN KEY (codigo_database) REFERENCES tbl_database(codigo)
	) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO  tbl_releasenotes(
	nm_sistema,
	dt_criacao,
	tp_ambiente_deploy,
	nm_ambiente_deploy, 
	vs_codigo_fonte, 
	vs_codigo_compilado,
	nm_coordenador_ti, 
	nm_coordenador_projeto, 
	tp_status_nr,
	nm_resumo,
	nm_problemas,
	nm_funcionalidades,
	nm_infraestrutura_software,
	codigo_qualitycode,
	codigo_database) 
values(
	'SINCO',
	'2008-02-23',
	'Padrao',
	'homologacao',
	'c20192333221',
	'00.00.001',
	'Coord de ti',
	'Coord de Projeto',
	'Aprovado',
	'nm_resumo',
	'nm_problemas',
	'nm_funcionalidades',
	'Roles1',
	1,
	1
);

INSERT INTO  tbl_releasenotes(
	nm_sistema,
	dt_criacao,
	tp_ambiente_deploy,
	nm_ambiente_deploy, 
	vs_codigo_fonte, 
	vs_codigo_compilado,
	nm_coordenador_ti, 
	nm_coordenador_projeto, 
	tp_status_nr,
	nm_resumo,
	nm_problemas,
	nm_funcionalidades,
	nm_infraestrutura_software,
	codigo_qualitycode,
	codigo_database) 
values(
	'SIRFG',
	'2008-02-23',
	'Padrao',
	'homologacao',
	'c20192333221',
	'00.00.001',
	'Coord de ti',
	'Coord de Projeto',
	'Reprovado',
	'nm_resumo',
	'nm_problemas',
	'nm_funcionalidades',
	'Roles2',
	2,
	2
);

INSERT INTO  tbl_releasenotes(
	nm_sistema,
	dt_criacao,
	tp_ambiente_deploy,
	nm_ambiente_deploy, 
	vs_codigo_fonte, 
	vs_codigo_compilado,
	nm_coordenador_ti, 
	nm_coordenador_projeto, 
	tp_status_nr,
	nm_resumo,
	nm_problemas,
	nm_funcionalidades,
	nm_infraestrutura_software,
	codigo_qualitycode,
	codigo_database) 
values(
	'SINCO',
	'2008-02-23',
	'Padrao',
	'homologacao',
	'c20192333221',
	'00.00.001',
	'Coord de ti',
	'Coord de Projeto',
	'Reprovado',
	'nm_resumo',
	'nm_problemas',
	'nm_funcionalidades',
	'Roles3',
	3,
	3
);





