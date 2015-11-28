/**
 * 
 */
package br.edu.unitri.rest.service.history;

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

import br.edu.unitri.dao.impl.history.PersonFeedbackDao;
import br.edu.unitri.model.history.PersonFeedback;


/**
 * @author marcos.fernando
 *
 */
@Path("/pessoa/feedback")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonFeedbackService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PersonFeedbackDao personFeedbackDao;
	
	@GET
	@Path("/todos")
	public List<PersonFeedback> listAll() {	
		return personFeedbackDao.findAll();
	}

	@GET
	@Path("/porId")
	public PersonFeedback getById(Long id) {
		return personFeedbackDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<PersonFeedback> listByEmail(PersonFeedback professor) {
		return personFeedbackDao.findByEntity(professor);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(PersonFeedback professor) {
		try {
			if (professor.getId() != null) {
				personFeedbackDao.update(professor);
			} else {
				personFeedbackDao.save(professor);
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
			PersonFeedback professor = new ObjectMapper().readValue(entidade, PersonFeedback.class);
			personFeedbackDao.deleteEntity(professor);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
