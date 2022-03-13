package br.com.testeMaven.view.command.impl;

import br.com.testeMaven.core.fachada.IFachada;
import br.com.testeMaven.core.fachada.impl.Fachada;
import br.com.testeMaven.view.command.ICommand;

public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();
	
}
