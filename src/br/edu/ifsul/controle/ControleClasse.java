package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.model.Classe;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleClasse")
@ViewScoped
public class ControleClasse implements Serializable {
	private Classe obj;
	private ClasseDAO<Classe> dao;

	public ControleClasse() {
		dao = new ClasseDAO<>();
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

	public ClasseDAO<Classe> getDao() {
		return dao;
	}

	public Classe getObj() {
		return obj;
	}

	public String listar() {
		return "/privado/classe/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Classe();
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

	public void setDao(ClasseDAO<Classe> dao) {
		this.dao = dao;
	}

	public void setObj(Classe obj) {
		this.obj = obj;
	}
}
