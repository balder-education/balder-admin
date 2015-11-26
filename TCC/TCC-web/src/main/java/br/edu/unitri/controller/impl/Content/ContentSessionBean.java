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
import br.edu.unitri.dao.impl.Content.ContentSessionDao;
import br.edu.unitri.model.Content.ContentSession;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContentSessionBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentSession contentSession = new ContentSession();
	private ContentSession contentSessionSel;
	private List<ContentSession> listConteudosSessao = new ArrayList<ContentSession>();
	
	@Inject
	private ContentSessionDao contentSessionDao;

	public ContentSessionBean() {
		super();
	}

	public ContentSession getContentSession() {
		return contentSession;
	}

	public void setContentSession(ContentSession contentSession) {
		this.contentSession = contentSession;
	}

	public ContentSession getContentSessionSel() {
		return contentSessionSel;
	}

	public void setContentSessionSel(ContentSession contentSessionSel) {
		this.contentSessionSel = contentSessionSel;
	}

	public List<ContentSession> getListConteudosSessao() {
		return listConteudosSessao;
	}

	public void setListConteudosSessao(List<ContentSession> listConteudosSessao) {
		this.listConteudosSessao = listConteudosSessao;
	}

	@PostConstruct
	public void init() {
		contentSession = new ContentSession();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (contentSession.getId() != null) {
			contentSessionDao.update(contentSession);
		} else {
			contentSessionDao.save(contentSession);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listConteudosSessao.clear();
		listConteudosSessao = contentSessionDao.findByEntity(contentSession);
		if (listConteudosSessao.size() == 0) {
			UtilBeanFaces.addMessage("Não existem sessões de conteúdos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (contentSessionSel != null) {
			contentSessionDao.deleteEntity(contentSessionSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setContentSession(getContentSessionSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listConteudosSessao = contentSessionDao.findAll();
	}

}
