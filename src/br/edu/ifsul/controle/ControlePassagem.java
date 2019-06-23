package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.dao.PassagemDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.model.Classe;
import br.edu.ifsul.model.Passagem;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controlePassagem")
@ViewScoped
public class ControlePassagem implements Serializable {
	private Passagem obj;
	private PassagemDAO<Passagem> dao;
	private Pessoa pessoa;
	private PessoaDAO<Pessoa> daoPessoa;
	private Classe classe;
	private ClasseDAO<Classe> daoClasse;

	public ControlePassagem() {
		dao = new PassagemDAO<>();
		daoPessoa = new PessoaDAO<>();
		daoClasse = new ClasseDAO<>();
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

	public Classe getClasse() {
		return classe;
	}

	public PassagemDAO<Passagem> getDao() {
		return dao;
	}

	public ClasseDAO<Classe> getDaoClasse() {
		return daoClasse;
	}

	public PessoaDAO<Pessoa> getDaoPessoa() {
		return daoPessoa;
	}

	public Passagem getObj() {
		return obj;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public String listar() {
		return "/privado/passagem/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Passagem();
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

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void setDao(PassagemDAO<Passagem> dao) {
		this.dao = dao;
	}

	public void setDaoClasse(ClasseDAO<Classe> daoClasse) {
		this.daoClasse = daoClasse;
	}

	public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
		this.daoPessoa = daoPessoa;
	}

	public void setObj(Passagem obj) {
		this.obj = obj;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
