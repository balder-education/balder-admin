/**
 * 
 */
package br.edu.unitri.dao.impl.address;

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
import br.edu.unitri.model.address.Cep;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class CepDao extends GenericDAO<Long, Cep> implements IGenericDao<Cep>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Cep entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Cep> findByEntity(Cep entity) {
		
		CriteriaQuery<Cep> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Cep> listAllEntity(Cep entity, int first, int max) {

		CriteriaQuery<Cep> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Cep entity, CriteriaBuilder cb, Root<Cep> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getZipCode() != null) {
			predicados.add(cb.equal(root.get("zipCode"), entity.getZipCode()));
		}
		if (entity.getStreet() != null) {
			predicados.add(cb.equal(root.get("street"), entity.getStreet()));
		}		
		if (entity.getCity() != null) {
			predicados.add(cb.equal(root.get("city"), entity.getCity()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Cep> query(Cep entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Cep> query = cb.createQuery(Cep.class);
		Root<Cep> root = query.from(Cep.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
