/**
 * 
 */
package br.edu.unitri.rest.service.Content;

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

import br.edu.unitri.dao.impl.Content.CourseDao;
import br.edu.unitri.model.Content.Course;

/**
 * @author marcos.fernando
 *
 */
@Path("/curso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CourseDao cursoDao;
	
	@GET
	@Path("/todos")
	public List<Course> listAll() {	
		return cursoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Course getById(Long id) {
		return cursoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Course> listByEmail(Course curso) {
		return cursoDao.findByEntity(curso);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Course curso) {
		try {
			if (curso.getId() != null) {
				cursoDao.update(curso);
			} else {
				cursoDao.save(curso);
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
			Course curso = new ObjectMapper().readValue(entidade, Course.class);
			cursoDao.deleteEntity(curso);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
