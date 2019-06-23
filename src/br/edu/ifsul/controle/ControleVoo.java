package br.edu.ifsul.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.model.Aeroporto;
import br.edu.ifsul.model.Voo;
import br.edu.ifsul.model.VooAgendado;
import br.edu.ifsul.util.Util;

@ManagedBean(name = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable {
	private Voo obj;
	private VooDAO<Voo> dao;
	private Aeroporto aeroporto;
	private AeroportoDAO<Aeroporto> daoAeroporto;
	private VooAgendado vooAgendado;
	private Boolean novoVooAgendado;

	public ControleVoo() {
		dao = new VooDAO<>();
		daoAeroporto = new AeroportoDAO<>();
	}

	public void adicionarEscala() {
		if (aeroporto != null) {
			if (!obj.getAeroportos().contains(aeroporto)) {
				obj.adicionarAeroporto(aeroporto);
				Util.mensagemInformacao("Aeroporto adicionado com sucesso!");
			} else {
				Util.mensagemErro("Aeroporto já existe na lista de Escalas!");
			}
		}
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

	public void editarVooAgendado(int indice) {
		vooAgendado = obj.getVoosAgendados().get(indice);
		novoVooAgendado = false;
	}

	public Aeroporto getAeroporto() {
		return aeroporto;
	}

	public VooDAO<Voo> getDao() {
		return dao;
	}

	public AeroportoDAO<Aeroporto> getDaoAeroporto() {
		return daoAeroporto;
	}

	public Boolean getNovoVooAgendado() {
		return novoVooAgendado;
	}

	public Voo getObj() {
		return obj;
	}

	public VooAgendado getVooAgendado() {
		return vooAgendado;
	}

	public String listar() {
		return "/privado/voo/listar?faces-redirect=true";
	}

	public void novo() {
		obj = new Voo();
	}

	public void novoVooAgendado() {
		vooAgendado = new VooAgendado();
		novoVooAgendado = true;
	}

	public void remover(Integer id) {
		obj = dao.localizar(id);
		if (dao.remove(obj)) {
			Util.mensagemInformacao(dao.getMensagem());
		} else {
			Util.mensagemErro(dao.getMensagem());
		}
	}

	public void removerEscala(int indice) {
		Object[] lista = obj.getAeroportos().toArray();
		Aeroporto a = (Aeroporto) lista[indice];
		obj.getAeroportos().remove(a);
		Util.mensagemInformacao("Aeroporto removido com sucesso!");
	}

	public void removerVooAgendado(int indice) {
		obj.removerVooAgendado(indice);
		Util.mensagemInformacao("Voo Agendado removido com sucesso!");
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

	public void salvarVooAgendado() {
		if (novoVooAgendado) {
			obj.adicionarVooAgendado(vooAgendado);
		}
		Util.mensagemInformacao("Voo Agendado persistido com sucesso!");
	}

	public void setAeroporto(Aeroporto aeroporto) {
		this.aeroporto = aeroporto;
	}

	public void setDao(VooDAO<Voo> dao) {
		this.dao = dao;
	}

	public void setDaoAeroporto(AeroportoDAO<Aeroporto> daoAeroporto) {
		this.daoAeroporto = daoAeroporto;
	}

	public void setNovoVooAgendado(Boolean novoVooAgendado) {
		this.novoVooAgendado = novoVooAgendado;
	}

	public void setObj(Voo obj) {
		this.obj = obj;
	}

	public void setVooAgendado(VooAgendado vooAendado) {
		this.vooAgendado = vooAendado;
	}

}
