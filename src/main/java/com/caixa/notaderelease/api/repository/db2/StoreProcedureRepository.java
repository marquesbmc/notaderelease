package com.caixa.notaderelease.api.repository.db2;

import com.caixa.notaderelease.api.model.db2.StoreProcedure;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;


public interface StoreProcedureRepository extends JpaRepository<StoreProcedure, Long> {
	
	 @Procedure(procedureName = "FUG.FUGSP58H_TESTE_CONEXAO_JDBC")
	  Iterable<StoreProcedure> procurarTudo();
	  
	  @Query(nativeQuery = true, value = "call FUG.FUGSP58G_TESTE_CONEXAO_JDBC()")
	  String[][] consultaSPs();
	  
	  @Query(nativeQuery = true, value = "call FUG.FUGSP58G_TESTE_CONEXAO_JDBC()")
	  List<String> consultaSP();
	  
	  @Procedure(procedureName = "FUG.FUGSP58H_TESTE_CONEXAO_JDBC")
	  Set<StoreProcedure> findAll2();
	
	

}
