package br.com.testeMaven.view.command;

import br.com.testeMaven.core.dominio.EntidadeDominio;
import br.com.testeMaven.core.dominio.Resultado;

public interface ICommand {
	
	public Resultado execute (EntidadeDominio entidade);
	
}
