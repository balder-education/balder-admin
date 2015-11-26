/**
 * 
 */
package br.edu.unitri.controller.impl.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.history.StudentHistoryDao;
import br.edu.unitri.model.history.StudentHistory;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class StudentHistoryBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentHistory studentHistory = new StudentHistory();
	private StudentHistory studentHistorySel;
	private List<StudentHistory> liststudentHistorys = new ArrayList<StudentHistory>();

	@Inject
	private StudentHistoryDao studentHistoryDao;

	public StudentHistoryBean() {
		super();
	}

	public StudentHistory getstudentHistory() {
		return studentHistory;
	}

	public void setstudentHistory(StudentHistory studentHistory) {
		this.studentHistory = studentHistory;
	}

	public StudentHistory getstudentHistorySel() {
		return studentHistorySel;
	}

	public void setstudentHistorySel(StudentHistory studentHistorySel) {
		this.studentHistorySel = studentHistorySel;
	}

	public List<StudentHistory> getListstudentHistorys() {
		return liststudentHistorys;
	}

	public void setListstudentHistorys(List<StudentHistory> liststudentHistorys) {
		this.liststudentHistorys = liststudentHistorys;
	}

	@PostConstruct
	public void init() {
		studentHistory = new StudentHistory();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (studentHistory.getId() != null) {
			studentHistoryDao.update(studentHistory);
		} else {
			studentHistoryDao.save(studentHistory);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		liststudentHistorys.clear();
		liststudentHistorys = studentHistoryDao.findByEntity(studentHistory);
		if (liststudentHistorys.size() == 0) {
			UtilBeanFaces.addMessage("Não existem históricos de alunos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (studentHistorySel != null) {
			studentHistoryDao.deleteEntity(studentHistorySel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setstudentHistory(getstudentHistorySel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		liststudentHistorys = studentHistoryDao.findAll();
	}
}
