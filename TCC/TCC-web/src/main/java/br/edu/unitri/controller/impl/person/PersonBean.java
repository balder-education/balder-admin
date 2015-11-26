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
import br.edu.unitri.dao.impl.person.PersonDao;
import br.edu.unitri.model.person.Person;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class PersonBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person = new Person();
	private Person personSel;
	private List<Person> listpersons = new ArrayList<Person>();

	@Inject
	private PersonDao personDao;

	public PersonBean() {
		super();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPersonSel() {
		return personSel;
	}

	public void setPersonSel(Person personSel) {
		this.personSel = personSel;
	}

	public List<Person> getListpersons() {
		return listpersons;
	}

	public void setListpersons(List<Person> listpersons) {
		this.listpersons = listpersons;
	}

	@PostConstruct
	public void init() {
		person = new Person();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (person.getId() != null) {
			personDao.update(person);
		} else {
			personDao.save(person);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listpersons.clear();
		listpersons = personDao.findByEntity(person);
		if (listpersons.size() == 0) {
			UtilBeanFaces.addMessage("Não existem pessoas para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (personSel != null) {
			if (personDao.deleteEntity(personSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setPerson(getPersonSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listpersons = personDao.findAll();
	}
}
