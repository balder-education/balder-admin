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

import br.edu.unitri.dao.impl.Content.ContentSessionDao;
import br.edu.unitri.model.Content.ContentSession;

/**
 * @author marcos.fernando
 *
 */
@Path("/conteudo/sessao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContentSessionService  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContentSessionDao conteudoDao;
	
	@GET
	@Path("/todos")
	public List<ContentSession> listAll() {	
		return conteudoDao.findAll();
	}

	@GET
	@Path("/porId")
	public ContentSession getById(Long id) {
		return conteudoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<ContentSession> listByEmail(ContentSession conteudo) {
		return conteudoDao.findByEntity(conteudo);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(ContentSession conteudo) {
		try {
			if (conteudo.getId() != null) {
				conteudoDao.update(conteudo);
			} else {
				conteudoDao.save(conteudo);
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
			ContentSession conteudo = new ObjectMapper().readValue(entidade, ContentSession.class);
			conteudoDao.deleteEntity(conteudo);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
