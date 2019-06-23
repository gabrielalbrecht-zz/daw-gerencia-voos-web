package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable {
	private Pessoa obj;
	private PessoaDAO<Pessoa> dao;

	public ControlePessoa() {
		dao = new PessoaDAO<>();
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

	public PessoaDAO<Pessoa> getDao() {
		return dao;
	}

	public Pessoa getObj() {
		return obj;
	}

	public String listar() {
		return "/privado/pessoa/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Pessoa();
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

	public void setDao(PessoaDAO<Pessoa> dao) {
		this.dao = dao;
	}

	public void setObj(Pessoa obj) {
		this.obj = obj;
	}
}
