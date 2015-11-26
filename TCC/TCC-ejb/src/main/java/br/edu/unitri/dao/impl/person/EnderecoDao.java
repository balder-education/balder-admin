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
import br.edu.unitri.model.person.Endereco;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class EnderecoDao extends GenericDAO<Long, Endereco> implements IGenericDao<Endereco>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Endereco entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Endereco> findByEntity(Endereco entity) {
		
		CriteriaQuery<Endereco> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Endereco> listAllEntity(Endereco entity, int first, int max) {

		CriteriaQuery<Endereco> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Endereco entity, CriteriaBuilder cb, Root<Endereco> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getPerson() != null) {
			predicados.add(cb.equal(root.get("person"), entity.getPerson()));
		}
		if (entity.getTipoEndereco() != null) {
			predicados.add(cb.equal(root.get("tipoEndereco"), entity.getTipoEndereco()));
		}
		if (entity.getAddress() != null) {
			predicados.add(cb.equal(root.get("address"), entity.getAddress()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Endereco> query(Endereco entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> query = cb.createQuery(Endereco.class);
		Root<Endereco> root = query.from(Endereco.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
