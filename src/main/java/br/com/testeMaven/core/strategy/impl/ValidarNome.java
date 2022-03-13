package br.com.testeMaven.core.strategy.impl;

import br.com.testeMaven.core.dominio.Aluno;
import br.com.testeMaven.core.dominio.EntidadeDominio;
import br.com.testeMaven.core.strategy.IStrategy;

/**
 * Classe para validar o campo nome do Aluno
 * @author Davi Machado
 * @date 13/03/2022
 */
public class ValidarNome implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		
		Aluno aluno = (Aluno) entidade;
		
		if(aluno.getNome() == null || aluno.getNome().equals("")) {
			return ("Favor insira um nome.");
		}
		else {
			return null;
		}
	}

}
