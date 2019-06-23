package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Pessoa;

public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {
	public PessoaDAO() {
		super();
		classePersistente = Pessoa.class;
	}
}
