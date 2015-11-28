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

import br.edu.unitri.dao.impl.Content.ArquivoDao;
import br.edu.unitri.model.Content.Arquivo;

/**
 * @author marcos.fernando
 *
 */
@Path("/arquivo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArquivoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ArquivoDao arquivoDao;
	
	@GET
	@Path("/todos")
	public List<Arquivo> listAll() {	
		return arquivoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Arquivo getById(Long id) {
		return arquivoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Arquivo> listByEmail(Arquivo arquivo) {
		return arquivoDao.findByEntity(arquivo);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Arquivo arquivo) {
		try {
			if (arquivo.getId() != null) {
				arquivoDao.update(arquivo);
			} else {
				arquivoDao.save(arquivo);
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
			Arquivo arquivo = new ObjectMapper().readValue(entidade, Arquivo.class);
			arquivoDao.deleteEntity(arquivo);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
