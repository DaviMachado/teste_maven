package br.com.testeMaven.core.dominio;

import java.util.List;

import br.com.testeMaven.core.dominio.EntidadeDominio;;

public class Resultado {
	private String mensagem;
    private List<EntidadeDominio> entidades;
    
    
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
