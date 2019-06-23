package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.model.Cidade;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleCidade")
@ViewScoped
public class ControleCidade implements Serializable {
	private Cidade obj;
	private CidadeDAO<Cidade> dao;

	public ControleCidade() {
		dao = new CidadeDAO<>();
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

	public CidadeDAO<Cidade> getDao() {
		return dao;
	}

	public Cidade getObj() {
		return obj;
	}

	public String listar() {
		return "/privado/cidade/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Cidade();
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

	public void setDao(CidadeDAO<Cidade> dao) {
		this.dao = dao;
	}

	public void setObj(Cidade obj) {
		this.obj = obj;
	}
}
