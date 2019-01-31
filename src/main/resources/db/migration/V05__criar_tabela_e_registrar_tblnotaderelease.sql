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
	problemas_conhecidos_notaderelease TEXT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease) values ('Hotdeploy', 'Homologação', 'SIFUG', 'cadastro', null, '2019-01-28', null, null,'C201901012323', '01.04.00.098', '18-39', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null);

INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease) values ('Normal', 'Producão', 'SIOFG', 'Unitario testes', null, '2019-03-01', null, null,'C201901014444', '01.04.00.111', '18-44', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null);

INSERT INTO tbl_notaderelease (tipo_deploy, ambiente_deploy, nome_sistema, status_aprovacao, data_aprovacao, data_cadastramento, data_instalacao, data_cancelamento, versao_codigo_fonte, versao_codigo_compilado, versao_modelo_banco, escopo_notaderelease, funcionalidades_notaderelease, problemas_conhecidos_notaderelease) values ('Hotdeploy', 'Homologação', 'SIFUG', 'cadastro', null, '2019-01-28', null, null,'C201901017070', '01.04.00.098', '18-39', 'Teste para novo ambiente do sifug com slots', 'Get escolha de slots', null);



