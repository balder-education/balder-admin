/**
 * 
 */
package br.edu.unitri.rest.service.address;

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

import br.edu.unitri.dao.impl.address.StateDao;
import br.edu.unitri.model.address.State;

/**
 * @author marcos.fernando
 *
 */
@Path("/estado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StateService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private StateDao estadoDao;
	
	@GET
	@Path("/todos")
	public List<State> listAll() {	
		return estadoDao.findAll();
	}

	@GET
	@Path("/porId")
	public State getById(Long id) {
		return estadoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<State> listByEmail(State estado) {
		return estadoDao.findByEntity(estado);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(State estado) {
		try {
			if (estado.getId() != null) {
				estadoDao.update(estado);
			} else {
				estadoDao.save(estado);
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
			State estado = new ObjectMapper().readValue(entidade, State.class);
			estadoDao.deleteEntity(estado);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
