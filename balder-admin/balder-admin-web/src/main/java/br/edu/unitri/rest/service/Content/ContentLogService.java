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

import br.edu.unitri.dao.impl.Content.ContentLogDao;
import br.edu.unitri.model.Content.ContentLog;

/**
 * @author marcos.fernando
 *
 */
@Path("/conteudo/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContentLogService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContentLogDao conteudoLogDao;
	
	@GET
	@Path("/todos")
	public List<ContentLog> listAll() {	
		return conteudoLogDao.findAll();
	}

	@GET
	@Path("/porId")
	public ContentLog getById(Long id) {
		return conteudoLogDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<ContentLog> listByEmail(ContentLog conteudoLog) {
		return conteudoLogDao.findByEntity(conteudoLog);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(ContentLog conteudoLog) {
		try {
			if (conteudoLog.getId() != null) {
				conteudoLogDao.update(conteudoLog);
			} else {
				conteudoLogDao.save(conteudoLog);
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
			ContentLog conteudoLog = new ObjectMapper().readValue(entidade, ContentLog.class);
			conteudoLogDao.deleteEntity(conteudoLog);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
