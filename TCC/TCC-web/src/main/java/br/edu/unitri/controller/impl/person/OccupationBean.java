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
import br.edu.unitri.dao.impl.person.OccupationDao;
import br.edu.unitri.model.person.Occupation;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class OccupationBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Occupation occupation = new Occupation();
	private Occupation occupationSel;
	private List<Occupation> listoccupations = new ArrayList<Occupation>();

	@Inject
	private OccupationDao occupationDao;

	public OccupationBean() {
		super();
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Occupation getOccupationSel() {
		return occupationSel;
	}

	public void setOccupationSel(Occupation occupationSel) {
		this.occupationSel = occupationSel;
	}

	public List<Occupation> getListoccupations() {
		return listoccupations;
	}

	public void setListoccupations(List<Occupation> listoccupations) {
		this.listoccupations = listoccupations;
	}

	@PostConstruct
	public void init() {
		occupation = new Occupation();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (occupation.getId() != null) {
			occupationDao.update(occupation);
		} else {
			occupationDao.save(occupation);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listoccupations.clear();
		listoccupations = occupationDao.findByEntity(occupation);
		if (listoccupations.size() == 0) {
			UtilBeanFaces.addMessage("Não existem ocupações para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (occupationSel != null) {
			occupationDao.deleteEntity(occupationSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setOccupation(getOccupationSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listoccupations = occupationDao.findAll();
	}
}
