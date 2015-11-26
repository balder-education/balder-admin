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
import br.edu.unitri.dao.impl.history.PersonFeedbackDao;
import br.edu.unitri.model.history.PersonFeedback;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class PersonFeedbackBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonFeedback feedback = new PersonFeedback();
	private PersonFeedback feedbackSel;
	private List<PersonFeedback> listfeedbacks = new ArrayList<PersonFeedback>();

	@Inject
	private PersonFeedbackDao feedbackDao;

	public PersonFeedbackBean() {
		super();
	}

	public PersonFeedback getfeedback() {
		return feedback;
	}

	public void setfeedback(PersonFeedback feedback) {
		this.feedback = feedback;
	}

	public PersonFeedback getfeedbackSel() {
		return feedbackSel;
	}

	public void setfeedbackSel(PersonFeedback feedbackSel) {
		this.feedbackSel = feedbackSel;
	}

	public List<PersonFeedback> getListfeedbacks() {
		return listfeedbacks;
	}

	public void setListfeedbacks(List<PersonFeedback> listfeedbacks) {
		this.listfeedbacks = listfeedbacks;
	}

	@PostConstruct
	public void init() {
		feedback = new PersonFeedback();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (feedback.getId() != null) {
			feedbackDao.update(feedback);
		} else {
			feedbackDao.save(feedback);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listfeedbacks.clear();
		listfeedbacks = feedbackDao.findByEntity(feedback);
		if (listfeedbacks.size() == 0) {
			UtilBeanFaces.addMessage("Não existem feedbacks para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (feedbackSel != null) {
			feedbackDao.deleteEntity(feedbackSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setfeedback(getfeedbackSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listfeedbacks = feedbackDao.findAll();
	}
}
