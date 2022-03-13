package br.com.testeMaven.view.command.impl;

import br.com.testeMaven.core.dominio.EntidadeDominio;
import br.com.testeMaven.core.dominio.Resultado;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}
	
}
