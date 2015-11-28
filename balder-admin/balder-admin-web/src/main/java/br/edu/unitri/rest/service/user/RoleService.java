/**
 * 
 */
package br.edu.unitri.rest.service.user;

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

import br.edu.unitri.dao.impl.user.RoleDao;
import br.edu.unitri.model.user.Role;

/**
 * @author marcos.fernando
 *
 */
@Path("/permissao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private RoleDao permissaoDao;
	
	@GET
	@Path("/todos")
	public List<Role> listAll() {	
		return permissaoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Role getById(Long id) {
		return permissaoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Role> listByEmail(Role permissao) {
		return permissaoDao.findByEntity(permissao);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Role permissao) {
		try {
			if (permissao.getId() != null) {
				permissaoDao.update(permissao);
			} else {
				permissaoDao.save(permissao);
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
			Role permissao = new ObjectMapper().readValue(entidade, Role.class);
			permissaoDao.deleteEntity(permissao);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
