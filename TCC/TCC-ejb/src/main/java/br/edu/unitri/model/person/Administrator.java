/**
 * 
 */
package br.edu.unitri.model.person;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.unitri.model.history.PersonFeedback;
import br.edu.unitri.model.user.User;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbManager")
public class Administrator extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}

	public Administrator(long id, String name, Date burnDate, String rg, String cpf, String email,
			Collection<Endereco> listaEnderecos, Collection<Contato> listaContatos,
			Collection<Occupation> listaOcupacao, User user, PersonFeedback feedback) {
		super(id, name, burnDate, rg, cpf, email, listaEnderecos, listaContatos, listaOcupacao, user, feedback);
	}

	public Administrator(String name, Date burnDate, String rg, String cpf, String email, Collection<Endereco> listaEnderecos,
			Collection<Contato> listaContatos, Collection<Occupation> listaOcupacao, User user,
			PersonFeedback feedback) {
		super(name, burnDate, rg, cpf, email, listaEnderecos, listaContatos, listaOcupacao, user, feedback);
	}

	public Administrator(String name, Date burnDate, String rg, String cpf, String email, User user,
			PersonFeedback feedback) {
		super(name, burnDate, rg, cpf, email, user, feedback);
	}

}
