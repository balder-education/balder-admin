/**
 * 
 */
package br.edu.unitri.repository;

/**
 * @author marcos.fernando
 *
 */
public interface CrudBean {
	
	/**
	 * Metodo utilizado para salvar a entidade que está sendo trabalhada na pagina web
	 */	
	public abstract void salvar();
	
	/**
	 * Metodo utilizado para realizar a busca referente a entidade que está sendo trabalhada na pagina web
	 */	
	public abstract void buscar();
	
	/**
	 * Metodo utilizado para realizar a exclusão referente a entidade que está sendo trabalhada na pagina web
	 */	
	public abstract void excluir();
	
	/**
	 * Metodo utilizado para realizar a edição da entidade que está sendo trabalhada na pagina web
	 */	
	public abstract void editar();
	
	/**
	 * Metodo utilizado para realizar a limpar a operação que está sendo realizada na pagina web
	 */	
	public abstract void limpar();
	
	
	/**
	 * Metodo utilizado para criar uma lista da entidade que está sendo trabalhada na pagina web
	 */	
	public abstract void listarTodos();

}
