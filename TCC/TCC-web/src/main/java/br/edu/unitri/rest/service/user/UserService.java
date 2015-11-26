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

import br.edu.unitri.dao.impl.user.UserDao;
import br.edu.unitri.model.user.User;

/**
 * @author marcos.fernando
 *
 */
@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UserDao userDao;
	
	@GET
	@Path("/todos")
	public List<User> listAll() {	
		return userDao.findAll();
	}

	@GET
	@Path("/porId")
	public User getById(Long id) {
		return userDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<User> listByEmail(User user) {
		return userDao.findByEntity(user);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(User user) {
		try {
			if (user.getId() != null) {
				userDao.update(user);
			} else {
				userDao.save(user);
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
			User user = new ObjectMapper().readValue(entidade, User.class);
			userDao.deleteEntity(user);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
