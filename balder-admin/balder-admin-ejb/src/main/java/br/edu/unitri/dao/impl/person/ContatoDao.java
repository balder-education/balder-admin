/**
 * 
 */
package br.edu.unitri.dao.impl.person;

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
import br.edu.unitri.model.person.Contato;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ContatoDao extends GenericDAO<Long, Contato> implements IGenericDao<Contato>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Contato entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Contato> findByEntity(Contato entity) {
		
		CriteriaQuery<Contato> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Contato> listAllEntity(Contato entity, int first, int max) {

		CriteriaQuery<Contato> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Contato entity, CriteriaBuilder cb, Root<Contato> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getPerson() != null) {
			predicados.add(cb.equal(root.get("person"), entity.getPerson()));
		}
		if (entity.getTipoContato() != null) {
			predicados.add(cb.equal(root.get("tipoContato"), entity.getTipoContato()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Contato> query(Contato entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Contato> query = cb.createQuery(Contato.class);
		Root<Contato> root = query.from(Contato.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
