package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.Aeroporto;

public class AeroportoDAO<TIPO> extends DAOGenerico<Aeroporto> implements Serializable {
	public AeroportoDAO() {
		super();
		classePersistente = Aeroporto.class;
	}
}
