CREATE TABLE tbl_qualidade (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_captura DATE,
	conceito CHAR(1),
	tot_testes_unitarios VARCHAR(10),
	tot_testes_funcionais VARCHAR(10),	
	tot_testes_falhas VARCHAR(10),
	tot_testes_erros VARCHAR(10),
	tot_prob_info VARCHAR(10),
	tot_prob_minor VARCHAR(10),
	tot_prob_major VARCHAR(10),
	tot_prob_blocker VARCHAR(10),
	tot_prob_critical VARCHAR(10),
	rel_testes TEXT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_qualidade ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2008-11-01', 'A', '12', '1','3', '33','12','33','1','0','0','0');

INSERT INTO tbl_qualidade ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2018-11-01', 'B', '12', '1','3', '33','12','33','1','0','0','0');

INSERT INTO tbl_qualidade ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2019-11-01', 'C', '12', '1','3', '33','12','33','1','0','0','0');



CREATE TABLE tbl_plataforma (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	conf_ambiente TEXT,
	rel_teste TEXT,
	rel_matriz_acesso TEXT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_plataforma (conf_ambiente, rel_teste, rel_matriz_acesso) values ('processos descritos', 'testes efetuados com sucesso', 'tabela de criterios criado');

INSERT INTO tbl_plataforma (conf_ambiente, rel_teste, rel_matriz_acesso) values ('slots instalados', 'testes efetuados com sucesso', 'tabela de vinculo criado');

INSERT INTO tbl_plataforma (conf_ambiente, rel_teste, rel_matriz_acesso) values ('slots instalados', 'testes efetuados com sucesso', 'tabela de vinculo criado');


CREATE TABLE tbl_notaderelease (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo_deploy VARCHAR(30),
	ambiente_deploy VARCHAR(30),
	nome_sistema VARCHAR(30),
	status_aprovacao VARCHAR(50),
	data_aprovacao DATE,
	data_cadastramento DATE,
	data_instalacao DATE,
	data_cancelamento DATE,
	versao_codigo_fonte VARCHAR(30),
	versao_codigo_compilado VARCHAR(30),
	versao_modelo_banco VARCHAR(30),
	escopo_notaderelease TEXT,
	funcionalidades_notaderelease TEXT,
	problemas_conhecidos_notaderelease TEXT,
	codigo_plataforma BIGINT(20),
	codigo_qualidade BIGINT(20),
	FOREIGN KEY (codigo_plataforma) REFERENCES tbl_plataforma(codigo),
	FOREIGN KEY (codigo_qualidade) REFERENCES tbl_qualidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease, codigo_plataforma, codigo_qualidade) values ('Hotdeploy', 'Homologação', 'SIFUG', 'cadastro', null, '2019-01-28', null, null,'C201901012323', '01.04.00.098', '18-39', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null,1 ,1);

INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease, codigo_plataforma, codigo_qualidade) values ('Normal', 'Producão', 'SIOFG', 'Unitario testes', null, '2019-03-01', null, null,'C201901014444', '01.04.00.111', '18-44', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null,2 ,2);

INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease, codigo_plataforma, codigo_qualidade) values ('Hotdeploy', 'Homologação', 'SIFUG', 'cadastro', null, '2019-01-28', null, null,'C201901017070', '01.04.00.098', '18-39', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null,1 ,3);



