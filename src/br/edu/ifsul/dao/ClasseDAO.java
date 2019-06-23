package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Classe;

public class ClasseDAO<TIPO> extends DAOGenerico<Classe> implements Serializable {
	public ClasseDAO() {
		super();
		classePersistente = Classe.class;
	}
}
