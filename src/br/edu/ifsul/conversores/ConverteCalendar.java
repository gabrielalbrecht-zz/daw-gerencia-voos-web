package br.edu.ifsul.conversores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterCalendar")
public class ConverteCalendar implements Serializable, Converter {
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String stringData) {
		if (stringData != null) {
			try {
				Calendar dt = Calendar.getInstance();
				dt.setTime(formatoData.parse(stringData));
				return dt;
			} catch (ParseException ex) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Calendar dt = (Calendar) o;
		return formatoData.format(dt.getTime());
	}
}
