package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Voo;

public class VooDAO<TIPO> extends DAOGenerico<Voo> implements Serializable {
	public VooDAO() {
		super();
		classePersistente = Voo.class;
	}
}
