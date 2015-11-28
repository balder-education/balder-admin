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

import br.edu.unitri.dao.impl.person.TeacherDao;
import br.edu.unitri.model.person.Teacher;

/**
 * @author marcos.fernando
 *
 */
@Path("/professor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TeacherDao professorDao;
	
	@GET
	@Path("/todos")
	public List<Teacher> listAll() {	
		return professorDao.findAll();
	}

	@GET
	@Path("/porId")
	public Teacher getById(Long id) {
		return professorDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Teacher> listByEmail(Teacher professor) {
		return professorDao.findByEntity(professor);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Teacher professor) {
		try {
			if (professor.getId() != null) {
				professorDao.update(professor);
			} else {
				professorDao.save(professor);
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
			Teacher professor = new ObjectMapper().readValue(entidade, Teacher.class);
			professorDao.deleteEntity(professor);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
