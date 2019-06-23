package br.edu.ifsul.dao;

import java.io.Serializable;

import br.edu.ifsul.model.VooAgendado;

public class VooAgendadoDAO<TIPO> extends DAOGenerico<VooAgendado> implements Serializable {
	public VooAgendadoDAO() {
		super();
		classePersistente = VooAgendado.class;
	}
}
