/**
 * 
 */
package br.edu.unitri.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author marcos.fernando
 *
 */
public interface IGenericDao<T extends Serializable> {
	
	/**
	 * Metodo que realiza a exclusão da entidade na base de dados
	 * @param T entidade que já foi persistida
	 * @return boolean identifica se operação foi realizada sem erros
	 */	
	boolean deleteEntity(T entity);

	/**
	 * Metodo que realiza a listagem referente a entidade
	 * @param T entidade usada como parametro da busca
	 * @return List listagem da entidade 
	 */	
	List<T> findByEntity(T entity);
	
	/**
	 * Metodo que realiza a listagem referente a entidade
	 * @param T entidade usada como parametro da busca
	 * @param first inicio da listagem
	 * @param max   fim da listagem
	 * @return List listagem da entidade 
	 */	
	List<T> listAllEntity(T entity, final int first, final int max);
	
	/**
	 * Metodo que realiza o filtro de uma determinada entidade
	 * @param T entidade 
	 * @param CriteriaBuilder query a ser utilizada
	 * @param Root - entidade raiz da consulta a ser relizada
	 * @return List<Predicate> filtros a serem aplicados na query
	 */	
	List<Predicate> getPredicados (T entity, CriteriaBuilder cb, Root<T> root);
	
	/**
	 * Metodo que realiza uma query de uma determinada entidade
	 * @param T entidade 
	 */	
	CriteriaQuery<T> query (T entity);


}
