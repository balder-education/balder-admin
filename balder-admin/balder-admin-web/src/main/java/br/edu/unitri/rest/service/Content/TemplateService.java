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

import br.edu.unitri.dao.impl.Content.TemplateDao;
import br.edu.unitri.model.Content.Template;

/**
 * @author marcos.fernando
 *
 */
@Path("/gabarito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemplateService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TemplateDao gabaritoDao;
	
	@GET
	@Path("/todos")
	public List<Template> listAll() {	
		return gabaritoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Template getById(Long id) {
		return gabaritoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Template> listByEmail(Template gabarito) {
		return gabaritoDao.findByEntity(gabarito);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Template gabarito) {
		try {
			if (gabarito.getId() != null) {
				gabaritoDao.update(gabarito);
			} else {
				gabaritoDao.save(gabarito);
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
			Template gabarito = new ObjectMapper().readValue(entidade, Template.class);
			gabaritoDao.deleteEntity(gabarito);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
