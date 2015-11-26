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
import br.edu.unitri.dao.impl.Content.CourseDao;
import br.edu.unitri.model.Content.Course;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class CourseBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course curso = new Course();
	private Course cursoSel;
	private List<Course> listCursos = new ArrayList<Course>();

	@Inject
	private CourseDao cursoDao;

	public CourseBean() {
		super();
	}

	public Course getCurso() {
		return curso;
	}

	public void setCurso(Course curso) {
		this.curso = curso;
	}

	public Course getCursoSel() {
		return cursoSel;
	}

	public void setCursoSel(Course cursoSel) {
		this.cursoSel = cursoSel;
	}

	public List<Course> getListCursos() {
		return listCursos;
	}

	public void setListCursos(List<Course> listCursos) {
		this.listCursos = listCursos;
	}

	@PostConstruct
	public void init() {
		curso = new Course();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (curso.getId() != null) {
			cursoDao.update(curso);
		} else {
			cursoDao.save(curso);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listCursos.clear();
		listCursos = cursoDao.findByEntity(curso);
		if (listCursos.size() == 0) {
			UtilBeanFaces.addMessage("Não existem cursos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (cursoSel != null) {
			if (cursoDao.deleteEntity(cursoSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setCurso(getCursoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listCursos = cursoDao.findAll();
	}
}
