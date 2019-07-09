
CREATE TABLE tbl_platform (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	pb_conf_ambiente TEXT,
	pb_testes_funcionais TEXT,
    pa_matriz_acesso TEXT,
    pa_store_procedure TEXT,
    pa_bind TEXT,
    pa_jcl TEXT,
    pa_cobol TEXT
    
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO tbl_platform (pb_conf_ambiente, pb_testes_funcionais, pa_matriz_acesso, pa_store_procedure, pa_bind, pa_jcl, pa_cobol) values ('nota de release', 'consulta cidadao', 'funcionalidade nova e recurso', 'spfug87654,spfug87654 ', 'bind bind', 'jcl928282', 'cobol');


INSERT INTO tbl_platform (pb_conf_ambiente, pb_testes_funcionais, pa_matriz_acesso, pa_store_procedure, pa_bind, pa_jcl, pa_cobol)
values('nota de release', 'consulta cidadao', 'funcionalidade nova e recurso', 'spfug87654,spfug87654 ', 'bind bind', 'jcl928282', 'cobol');

INSERT INTO tbl_platform (pb_conf_ambiente, pb_testes_funcionais, pa_matriz_acesso, pa_store_procedure, pa_bind, pa_jcl, pa_cobol) values ('nota de release', 'consulta cidadao', 'funcionalidade nova e recurso', 'spfug87654,spfug87654 ', 'bind bind', 'jcl928282', 'cobol');

INSERT INTO tbl_platform (pb_conf_ambiente, pb_testes_funcionais, pa_matriz_acesso, pa_store_procedure, pa_bind, pa_jcl, pa_cobol) values ('nota de release', 'consulta cidadao', 'funcionalidade nova e recurso', 'spfug87654,spfug87654 ', 'bind bind', 'jcl928282', 'cobol');


