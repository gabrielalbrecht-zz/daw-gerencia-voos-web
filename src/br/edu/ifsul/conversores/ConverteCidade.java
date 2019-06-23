package br.edu.ifsul.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Cidade;

@FacesConverter(value = "converterCidade")
public class ConverteCidade implements Serializable, Converter {
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		if (string == null || string.equalsIgnoreCase("Selecione um registro")) {
			return null;
		} else {
			return EntityManagerUtil.getEntityManager().find(Cidade.class, Integer.parseInt(string));
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o == null) {
			return null;
		}
		Cidade p = (Cidade) o;
		return p.getId().toString();
	}
}
