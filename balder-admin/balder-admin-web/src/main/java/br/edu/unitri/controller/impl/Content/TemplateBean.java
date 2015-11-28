/**
 * 
 */
package br.edu.unitri.controller.impl.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.Content.TemplateDao;
import br.edu.unitri.model.Content.Template;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class TemplateBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Template template = new Template();
	private Template templateSel;
	private List<Template> listtemplates = new ArrayList<Template>();
	
	@Inject
	private TemplateDao templateDao;

	public TemplateBean() {
		super();
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Template getTemplateSel() {
		return templateSel;
	}

	public void setTemplateSel(Template templateSel) {
		this.templateSel = templateSel;
	}

	public List<Template> getListtemplates() {
		return listtemplates;
	}

	public void setListtemplates(List<Template> listtemplates) {
		this.listtemplates = listtemplates;
	}

	@PostConstruct
	public void init() {
		template = new Template();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (template.getId() != null) {
			templateDao.update(template);
		} else {
			templateDao.save(template);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listtemplates.clear();
		listtemplates = templateDao.findByEntity(template);
		if (listtemplates.size() == 0) {
			UtilBeanFaces.addMessage("Não existem templates para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (templateSel != null) {
			if (templateDao.deleteEntity(templateSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setTemplate(getTemplateSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listtemplates = templateDao.findAll();
	}
}
