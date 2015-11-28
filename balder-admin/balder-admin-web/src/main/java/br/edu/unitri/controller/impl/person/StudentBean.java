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
import br.edu.unitri.dao.impl.person.StudentDao;
import br.edu.unitri.model.person.Student;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class StudentBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student estudante = new Student();
	private Student estudanteSel;
	private List<Student> listestudantes = new ArrayList<Student>();

	@Inject
	private StudentDao estudanteDao;

	public StudentBean() {
		super();
	}

	public Student getStudent() {
		return estudante;
	}

	public void setStudent(Student estudante) {
		this.estudante = estudante;
	}

	public Student getStudentSel() {
		return estudanteSel;
	}

	public void setStudentSel(Student estudanteSel) {
		this.estudanteSel = estudanteSel;
	}

	public List<Student> getListestudantes() {
		return listestudantes;
	}

	public void setListestudantes(List<Student> listestudantes) {
		this.listestudantes = listestudantes;
	}

	@PostConstruct
	public void init() {
		estudante = new Student();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (estudante.getId() != null) {
			estudanteDao.update(estudante);
		} else {
			estudanteDao.save(estudante);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listestudantes.clear();
		listestudantes = estudanteDao.findByEntity(estudante);
		if (listestudantes.size() == 0) {
			UtilBeanFaces.addMessage("Não existem estudantes para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (estudanteSel != null) {
			estudanteDao.deleteEntity(estudanteSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setStudent(getStudentSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listestudantes = estudanteDao.findAll();
	}
}
