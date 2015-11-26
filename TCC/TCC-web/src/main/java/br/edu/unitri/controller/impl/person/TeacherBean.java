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
import br.edu.unitri.dao.impl.person.TeacherDao;
import br.edu.unitri.model.person.Teacher;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class TeacherBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Teacher professor = new Teacher();
	private Teacher professorSel;
	private List<Teacher> listprofessors = new ArrayList<Teacher>();

	@Inject
	private TeacherDao professorDao;

	public TeacherBean() {
		super();
	}

	public Teacher getProfessor() {
		return professor;
	}

	public void setProfessor(Teacher professor) {
		this.professor = professor;
	}

	public Teacher getProfessorSel() {
		return professorSel;
	}

	public void setProfessorSel(Teacher professorSel) {
		this.professorSel = professorSel;
	}

	public List<Teacher> getListprofessors() {
		return listprofessors;
	}

	public void setListprofessors(List<Teacher> listprofessors) {
		this.listprofessors = listprofessors;
	}

	@PostConstruct
	public void init() {
		professor = new Teacher();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (professor.getId() != null) {
			professorDao.update(professor);
		} else {
			professorDao.save(professor);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listprofessors.clear();
		listprofessors = professorDao.findByEntity(professor);
		if (listprofessors.size() == 0) {
			UtilBeanFaces.addMessage("Não existem professores para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (professorSel != null) {
			professorDao.deleteEntity(professorSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setProfessor(getProfessorSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listprofessors = professorDao.findAll();
	}
}
