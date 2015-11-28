package br.edu.unitri.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author marcos.fernando
 *
 */
/**
 * Classe abstrata para operações CRUD na base de dados
 * @param L identificador da classe
 * @param T entidade
 */	
@Stateless
@LocalBean
public abstract class GenericDAO<L,T> {
	
	@Inject
	private EntityManager manager;

	/**
	 * Metodo que busca a entidade pelo seu identificador
	 * @param PK identificador utilizado para busca
	 * @return T entidade a ser retornada
	 */	
	@SuppressWarnings("unchecked")
	public T getById(Long pk) {
		return (T) manager.find(getTypeClass(), pk);
	}

	/**
	 * Metodo que salva a entidade na base de dados
	 * @param T entidade que será persistida
	 */	
	public void save(T entity) {
		manager.persist(entity);
	}

	/**
	 * Metodo que atualiza a entidade na base de dados
	 * @param T entidade que será persistida
	 */	
	public void update(T entity) {
		manager.merge(entity);
	}

	/**
	 * Metodo que realiza a exclusão de uma entidade na base de dados
	 * @param T entidade que será excluida
	 */	
	public void delete(T entity) {
		manager.remove(entity);
	}

	/**
	 * Metodo que realiza a listagem referente a entidade
	 * @return List listagem da entidade 
	 */	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return manager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
	}
	
	/**
	 * Metodo que realiza a listagem referente a entidade
	 * @return List listagem da entidade com inicio e fim 
	 * @param first inicio da listagem
	 * @param max   fim da listagem
	 */	
	@SuppressWarnings("unchecked")
	public List<T> findAll(final int first, final int max) {
		return manager.createQuery(("FROM " + getTypeClass().getName())).setFirstResult(first).setMaxResults(max).getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}
}