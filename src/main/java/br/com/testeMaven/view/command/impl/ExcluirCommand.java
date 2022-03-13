package br.com.testeMaven.view.command.impl;

import br.com.testeMaven.core.dominio.EntidadeDominio;
import br.com.testeMaven.core.dominio.Resultado;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}
