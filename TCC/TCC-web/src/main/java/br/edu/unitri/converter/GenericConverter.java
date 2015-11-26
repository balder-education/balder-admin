/**
 * 
 */
package br.edu.unitri.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@Named
@FacesConverter(value="generic")
public class GenericConverter implements Converter, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if ((value != null) && (value != "")) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null
                && !"".equals(value)) {
            SimpleEntity entity = (SimpleEntity) value;
            this.addAttribute(component, entity);
            Long codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }
        return (String) value;
    }
	
	protected void addAttribute(UIComponent component, SimpleEntity o) {
        String key = o.getId().toString(); 
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
	

}
