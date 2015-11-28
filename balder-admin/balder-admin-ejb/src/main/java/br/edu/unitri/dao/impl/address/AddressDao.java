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
import br.edu.unitri.model.address.Address;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class AddressDao extends GenericDAO<Long, Address> implements IGenericDao<Address>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Address entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Address> findByEntity(Address entity) {
		
		CriteriaQuery<Address> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Address> listAllEntity(Address entity, int first, int max) {

		CriteriaQuery<Address> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Address entity, CriteriaBuilder cb, Root<Address> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getReferencia() != null) {
			predicados.add(cb.equal(root.get("referencia"), entity.getReferencia()));
		}
		if (entity.getComplement() != null) {
			predicados.add(cb.equal(root.get("complement"), entity.getComplement()));
		}		
		if (entity.getNumber() > 0) {
			predicados.add(cb.equal(root.get("number"), entity.getNumber()));
		}		
		if (entity.getCep() != null) {
			predicados.add(cb.equal(root.get("cep"), entity.getCep()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Address> query(Address entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Address> query = cb.createQuery(Address.class);
		Root<Address> root = query.from(Address.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
