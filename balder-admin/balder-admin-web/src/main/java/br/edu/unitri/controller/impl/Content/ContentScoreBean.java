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
import br.edu.unitri.dao.impl.Content.ContentScoreDao;
import br.edu.unitri.model.Content.ContentScore;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContentScoreBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentScore contentScore = new ContentScore();
	private ContentScore contentScoreSel;
	private List<ContentScore> listContentScore = new ArrayList<ContentScore>();
	
	@Inject
	private ContentScoreDao contentScoreDao;
	
	public ContentScoreBean() {
		super();
	}

	public ContentScore getContentScore() {
		return contentScore;
	}

	public void setContentScore(ContentScore contentScore) {
		this.contentScore = contentScore;
	}

	public ContentScore getContentScoreSel() {
		return contentScoreSel;
	}

	public void setContentScoreSel(ContentScore contentScoreSel) {
		this.contentScoreSel = contentScoreSel;
	}

	public List<ContentScore> getListContentScore() {
		return listContentScore;
	}

	public void setListContentScore(List<ContentScore> listContentScore) {
		this.listContentScore = listContentScore;
	}
	
	@PostConstruct
	public void init() {
		contentScore = new ContentScore();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (contentScore.getId() != null) {
			contentScoreDao.update(contentScore);
		} else {
			contentScoreDao.save(contentScore);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listContentScore.clear();
		listContentScore = contentScoreDao.findByEntity(contentScore);
		if (listContentScore.size() == 0) {
			UtilBeanFaces.addMessage("Não existem pontuações de conteúdos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (contentScoreSel != null) {
			contentScoreDao.deleteEntity(contentScoreSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setContentScore(getContentScoreSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listContentScore = contentScoreDao.findAll();
	}

}
