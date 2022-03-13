package br.com.testeMaven.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.testeMaven.core.dao.IDAO;
import br.com.testeMaven.core.dao.impl.AlunoDAO;
import br.com.testeMaven.core.dominio.Aluno;
import br.com.testeMaven.core.dominio.EntidadeDominio;
import br.com.testeMaven.core.dominio.Resultado;
import br.com.testeMaven.core.fachada.IFachada;
import br.com.testeMaven.core.strategy.IStrategy;
import br.com.testeMaven.core.strategy.impl.ValidarDataNascimento;
import br.com.testeMaven.core.strategy.impl.ValidarNome;

public class Fachada implements IFachada {

	private Resultado resultado;
	private static Map<String, IDAO> daos;

	/* ------------ Declaração de TODAS as Strategy's ------------ */
	ValidarNome vNome = new ValidarNome();
	ValidarDataNascimento vDataNascimento = new ValidarDataNascimento();
	/* ------------------------------------------------------------ */
	
	/* ------------ Declaração das Listas de Strategy's dos Dominios ------------ */
	/* ------------ SALVAR ------------ */
	List<IStrategy> regrasSalvarAluno = new ArrayList<>();
	/* ------------ CONSULTAR ------------ */
	List<IStrategy> regrasConsultarAluno = new ArrayList<>();
	/* ------------ ALTERAR ------------ */
	List<IStrategy> regrasAlterarAluno = new ArrayList<>();
	/* ------------ EXCLUIR ------------ */
	List<IStrategy> regrasExcluirAluno = new ArrayList<>();
	/* -------------------------------------------------------------------------- */
	
	/* ------------ Declaração dos MAP's das Regras de Neg�cios dos Dominios ------------ */
	Map<String, List<IStrategy>> regrasAluno = new HashMap<>();
	/* ----------------------------------------------------------------------------------- */
	
	/* ------------ Declaração da Regra de Neg�cio Geral ------------ */
	Map<String, Map<String, List<IStrategy>>> regrasGeral = new HashMap<>();
	/* --------------------------------------------------------------- */

	// Construtor da Fachada
	public Fachada() {
		// Mapa dos DAO's
		daos = new HashMap<String, IDAO>();

		// Criando instancias dos DAOS a serem utilizados,
		// adicionando cada dado no MAP indexado pelo nome da classe
		daos.put(Aluno.class.getName(), new AlunoDAO());
		
		/* ----- Adicionando as Strategy's na lista do Cliente ----- */
		/* ----- SALVAR ----- */
		//regrasSalvarAluno.add(vNome);
		//regrasSalvarAluno.add(vDataNascimento);
		/* ----- ALTERAR ----- */
		/* ----- CONSULTAR ----- */
		/* ----- EXCLUIR ----- */
		/* ---------------------------------------------------------- */

		/* ----- REGRAS DA ENTIDADE ALUNO ----- */
		/* ----- SALVAR ----- */
		regrasAluno.put("SALVAR", regrasSalvarAluno);
		/* ----- CONSULTAR ----- */
		regrasAluno.put("CONSULTAR", regrasConsultarAluno);
		/* ----- ALTERAR ----- */
		regrasAluno.put("ALTERAR", regrasAlterarAluno);
		/* ----- EXCLUIR ----- */
		regrasAluno.put("EXCLUIR", regrasExcluirAluno);
		/* -------------------------------------- */
		
		/* ----- REGRAS GERAIS ----- */
		regrasGeral.put(Aluno.class.getName(), regrasAluno);
		/* -------------------------- */
	}

	/*---SALVAR---*/
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		// retornar o nome do pacote com o nome da classe desta entidade de dominio
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null || msg == "") {
			// Obtem o DAO correspondente ao nome do pacote com o nome da classe,
			// que esta dentro do HashMap do "daos"
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);

				// cria uma lista para mostrar os Alunos salvos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Salvar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---ALTERAR---*/
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);

				// cria uma lista para mostrar os Alunos alterados
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Alterar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---EXCLUIR---*/
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);

				// cria uma lista para mostrar os Alunos excluidos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Excluir o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---CONSULTAR---*/
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Consulta o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	// Método para executar as regras de negocio / Strategy
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String msg = "";

		String nmClasse = entidade.getClass().getName();
		
		Map<String, List<IStrategy>> regrasDaEntidade = regrasGeral.get(nmClasse);
		
		List<IStrategy> regrasDaOperacao = regrasDaEntidade.get(operacao);
		
		for (IStrategy regra : regrasDaOperacao) {
			String resultado = regra.validar(entidade);
			if (resultado != null) {
				msg += "- " + resultado + "<br>";
			}
		}

		return msg;
	}
}
