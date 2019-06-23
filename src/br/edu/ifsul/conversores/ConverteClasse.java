package br.edu.ifsul.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Classe;

@FacesConverter(value = "converterClasse")
public class ConverteClasse implements Serializable, Converter {
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		if (string == null || string.equalsIgnoreCase("Selecione um registro")) {
			return null;
		} else {
			return EntityManagerUtil.getEntityManager().find(Classe.class, Integer.parseInt(string));
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o == null) {
			return null;
		}
		Classe p = (Classe) o;
		return p.getId().toString();
	}
}
