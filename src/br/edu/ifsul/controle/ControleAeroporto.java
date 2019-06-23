package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.model.Aeroporto;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleAeroporto")
@ViewScoped
public class ControleAeroporto implements Serializable {
	private Aeroporto obj;
	private AeroportoDAO<Aeroporto> dao;

	public ControleAeroporto() {
		dao = new AeroportoDAO<>();
	}

	public String cancelar() {
		return "listar?faces-redirect=true";
	}

	public void editar(Integer id) {
		try {
			obj = dao.localizar(id);
		} catch (Exception e) {
			Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
		}
	}

	public AeroportoDAO<Aeroporto> getDao() {
		return dao;
	}

	public Aeroporto getObj() {
		return obj;
	}

	public String listar() {
		return "/privado/aeroporto/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Aeroporto();
	}

	public void remover(Integer id) {
		obj = dao.localizar(id);
		if (dao.remove(obj)) {
			Util.mensagemInformacao(dao.getMensagem());
		} else {
			Util.mensagemErro(dao.getMensagem());
		}
	}

	public void salvar() {
		boolean persistiu;
		if (obj.getId() == null) {
			persistiu = dao.persist(obj);
		} else {
			persistiu = dao.merge(obj);
		}
		if (persistiu) {
			Util.mensagemInformacao(dao.getMensagem());
		} else {
			Util.mensagemErro(dao.getMensagem());
		}
	}

	public void setDao(AeroportoDAO<Aeroporto> dao) {
		this.dao = dao;
	}

	public void setObj(Aeroporto obj) {
		this.obj = obj;
	}
}
