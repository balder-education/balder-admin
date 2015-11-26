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

import br.edu.unitri.dao.impl.person.ContatoDao;
import br.edu.unitri.model.person.Contato;

/**
 * @author marcos.fernando
 *
 */
@Path("/contatoPessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContatoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContatoDao contatoDao;
	
	@GET
	@Path("/todos")
	public List<Contato> listAll() {	
		return contatoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Contato getById(Long id) {
		return contatoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Contato> listByEmail(Contato contato) {
		return contatoDao.findByEntity(contato);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Contato contato) {
		try {
			if (contato.getId() != null) {
				contatoDao.update(contato);
			} else {
				contatoDao.save(contato);
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
			Contato contato = new ObjectMapper().readValue(entidade, Contato.class);
			contatoDao.deleteEntity(contato);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
