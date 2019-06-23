package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Cidade;

public class CidadeDAO<TIPO> extends DAOGenerico<Cidade> implements Serializable {
	public CidadeDAO() {
		super();
		classePersistente = Cidade.class;
	}
}
