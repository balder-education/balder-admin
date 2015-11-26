/**
 * 
 */
package br.edu.unitri.controller.impl.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.person.AdministratorDao;
import br.edu.unitri.model.person.Administrator;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class AdministratorBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Administrator administrator = new Administrator();
	private Administrator administratorSel;
	private List<Administrator> listadministrators = new ArrayList<Administrator>();

	@Inject
	private AdministratorDao administratorDao;

	public AdministratorBean() {
		super();
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Administrator getAdministratorSel() {
		return administratorSel;
	}

	public void setAdministratorSel(Administrator administratorSel) {
		this.administratorSel = administratorSel;
	}

	public List<Administrator> getListadministrators() {
		return listadministrators;
	}

	public void setListadministrators(List<Administrator> listadministrators) {
		this.listadministrators = listadministrators;
	}

	@PostConstruct
	public void init() {
		administrator = new Administrator();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (administrator.getId() != null) {
			administratorDao.update(administrator);
		} else {
			administratorDao.save(administrator);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listadministrators.clear();
		listadministrators = administratorDao.findByEntity(administrator);
		if (listadministrators.size() == 0) {
			UtilBeanFaces.addMessage("Não existem administrators para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (administratorSel != null) {
			administratorDao.deleteEntity(administratorSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setAdministrator(getAdministratorSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listadministrators = administratorDao.findAll();
	}
}
