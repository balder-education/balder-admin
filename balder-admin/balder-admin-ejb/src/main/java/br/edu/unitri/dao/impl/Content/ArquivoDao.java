/**
 * 
 */
package br.edu.unitri.dao.impl.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.unitri.dao.GenericDAO;
import br.edu.unitri.dao.IGenericDao;
import br.edu.unitri.model.Content.Arquivo;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ArquivoDao extends GenericDAO<Long, Arquivo> implements IGenericDao<Arquivo>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Arquivo entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Arquivo> findByEntity(Arquivo entity) {
		
		CriteriaQuery<Arquivo> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Arquivo> listAllEntity(Arquivo entity, int first, int max) {

		CriteriaQuery<Arquivo> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Arquivo entity, CriteriaBuilder cb, Root<Arquivo> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getConteudo() != null) {
			predicados.add(cb.equal(root.get("conteudo"), entity.getConteudo()));
		}
		if (entity.getTipoConteudo() != null) {
			predicados.add(cb.equal(root.get("tipoConteudo"), entity.getTipoConteudo()));
		}
		if (entity.getNomeImagem() != null) {
			predicados.add(cb.equal(root.get("nomeImagem"), entity.getNomeImagem()));
		}
		if (entity.getExtensao() != null) {
			predicados.add(cb.equal(root.get("extensao"), entity.getExtensao()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Arquivo> query(Arquivo entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Arquivo> query = cb.createQuery(Arquivo.class);
		Root<Arquivo> root = query.from(Arquivo.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
