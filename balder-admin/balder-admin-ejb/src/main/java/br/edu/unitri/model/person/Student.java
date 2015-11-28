/**
 * 
 */
package br.edu.unitri.model.person;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.unitri.enumerators.TipoLevelStudent;
import br.edu.unitri.model.history.PersonFeedback;
import br.edu.unitri.model.history.StudentHistory;
import br.edu.unitri.model.user.User;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbStudent")
public class Student extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoLevelStudent tipoLevelStudent;

	private StudentHistory history;

	public Student() {
		super();
	}

	public Student(long id, String name, Date burnDate, String rg, String cpf, String email,
			Collection<Endereco> listaEnderecos, Collection<Contato> listaContatos,
			Collection<Occupation> listaOcupacao, User user, PersonFeedback feedback) {
		super(id, name, burnDate, rg, cpf, email, listaEnderecos, listaContatos, listaOcupacao, user, feedback);
	}

	public Student(String name, Date burnDate, String rg, String cpf, String email, Collection<Endereco> listaEnderecos,
			Collection<Contato> listaContatos, Collection<Occupation> listaOcupacao, User user,
			PersonFeedback feedback) {
		super(name, burnDate, rg, cpf, email, listaEnderecos, listaContatos, listaOcupacao, user, feedback);
	}

	public Student(String name, Date burnDate, String rg, String cpf, String email, User user,
			PersonFeedback feedback) {
		super(name, burnDate, rg, cpf, email, user, feedback);
	}

	public Student(TipoLevelStudent tipoLevelStudent, StudentHistory history) {
		super();
		this.tipoLevelStudent = tipoLevelStudent;
		this.history = history;
	}

	public TipoLevelStudent getTipoLevelStudent() {
		return tipoLevelStudent;
	}

	public void setTipoLevelStudent(TipoLevelStudent tipoLevelStudent) {
		this.tipoLevelStudent = tipoLevelStudent;
	}

	public StudentHistory getHistory() {
		return history;
	}

	public void setHistory(StudentHistory history) {
		this.history = history;
	}

}
