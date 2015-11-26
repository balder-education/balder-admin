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

import br.edu.unitri.dao.impl.person.StudentDao;
import br.edu.unitri.model.person.Student;

/**
 * @author marcos.fernando
 *
 */
@Path("/estudante")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private StudentDao estudanteDao;
	
	@GET
	@Path("/todos")
	public List<Student> listAll() {	
		return estudanteDao.findAll();
	}

	@GET
	@Path("/porId")
	public Student getById(Long id) {
		return estudanteDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Student> listByEmail(Student estudante) {
		return estudanteDao.findByEntity(estudante);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Student estudante) {
		try {
			if (estudante.getId() != null) {
				estudanteDao.update(estudante);
			} else {
				estudanteDao.save(estudante);
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
			Student estudante = new ObjectMapper().readValue(entidade, Student.class);
			estudanteDao.deleteEntity(estudante);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
