CREATE TABLE tbl_quality (
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

INSERT INTO tbl_quality ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2008-11-01', 'A', '12', '1','3', '33','12','33','1','0','0','0');

INSERT INTO tbl_quality ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2018-11-01', 'B', '12', '1','3', '33','12','33','1','0','0','0');

INSERT INTO tbl_quality ( data_captura, conceito, tot_testes_unitarios, tot_testes_funcionais, tot_testes_falhas, tot_testes_erros, tot_prob_info, tot_prob_minor, tot_prob_major, tot_prob_blocker, tot_prob_critical, rel_testes ) values ('2019-11-01', 'C', '12', '1','3', '33','12','33','1','0','0','0');
