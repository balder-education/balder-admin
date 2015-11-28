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

import br.edu.unitri.dao.impl.person.AdministratorDao;
import br.edu.unitri.model.person.Administrator;

/**
 * @author marcos.fernando
 *
 */
@Path("/administrador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministradorService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AdministratorDao administradorDao;
	
	@GET
	@Path("/todos")
	public List<Administrator> listAll() {	
		return administradorDao.findAll();
	}

	@GET
	@Path("/porId")
	public Administrator getById(Long id) {
		return administradorDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Administrator> listByEmail(Administrator administrador) {
		return administradorDao.findByEntity(administrador);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Administrator administrador) {
		try {
			if (administrador.getId() != null) {
				administradorDao.update(administrador);
			} else {
				administradorDao.save(administrador);
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
			Administrator administrador = new ObjectMapper().readValue(entidade, Administrator.class);
			administradorDao.deleteEntity(administrador);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
