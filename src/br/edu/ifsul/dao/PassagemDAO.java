package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Passagem;

public class PassagemDAO<TIPO> extends DAOGenerico<Passagem> implements Serializable {
	public PassagemDAO() {
		super();
		classePersistente = Passagem.class;
	}
}
