/**
 * 
 */
package br.edu.unitri.rest.service.person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.edu.unitri.dao.impl.person.PersonDao;
import br.edu.unitri.model.person.Person;

/**
 * @author marcos.fernando
 *
 */
@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PersonDao pessoaDao;
	
	@GET
	@Path("/todos")
	public List<Person> listAll() {	
		return pessoaDao.findAll();
	}

	@GET
	@Path("/porId")
	public Person getById(Long id) {
		return pessoaDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Person> listByEmail(Person pessoa) {
		return pessoaDao.findByEntity(pessoa);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Person pessoa) {
		try {
			if (pessoa.getId() != null) {
				pessoaDao.update(pessoa);
			} else {
				pessoaDao.save(pessoa);
			}
			return Response.ok().build();
		} catch (Exception ex) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("erro", ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
		}
	}
	
	@DELETE
	@Path("/excluir/{entidade}")	
	public Response excluir(@PathParam("entidade") String entidade) throws Exception{
		try {			
			Person pessoa = new ObjectMapper().readValue(entidade, Person.class);
			pessoaDao.deleteEntity(pessoa);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
