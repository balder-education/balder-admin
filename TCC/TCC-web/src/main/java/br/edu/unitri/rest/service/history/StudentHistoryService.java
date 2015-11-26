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

import br.edu.unitri.dao.impl.history.StudentHistoryDao;
import br.edu.unitri.model.history.StudentHistory;

/**
 * @author marcos.fernando
 *
 */
@Path("/estudante/historico")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentHistoryService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private StudentHistoryDao historicoDao;
	
	@GET
	@Path("/todos")
	public List<StudentHistory> listAll() {	
		return historicoDao.findAll();
	}

	@GET
	@Path("/porId")
	public StudentHistory getById(Long id) {
		return historicoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<StudentHistory> listByEmail(StudentHistory historico) {
		return historicoDao.findByEntity(historico);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(StudentHistory historico) {
		try {
			if (historico.getId() != null) {
				historicoDao.update(historico);
			} else {
				historicoDao.save(historico);
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
			StudentHistory historico = new ObjectMapper().readValue(entidade, StudentHistory.class);
			historicoDao.deleteEntity(historico);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
