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

import br.edu.unitri.dao.impl.address.CityDao;
import br.edu.unitri.model.address.City;

/**
 * @author marcos.fernando
 *
 */
@Path("/cidade")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CityService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CityDao cidadeDao;
	
	@GET
	@Path("/todos")
	public List<City> listAll() {	
		return cidadeDao.findAll();
	}

	@GET
	@Path("/porId")
	public City getById(Long id) {
		return cidadeDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<City> listByEmail(City cidade) {
		return cidadeDao.findByEntity(cidade);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(City cidade) {
		try {
			if (cidade.getId() != null) {
				cidadeDao.update(cidade);
			} else {
				cidadeDao.save(cidade);
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
			City cidade = new ObjectMapper().readValue(entidade, City.class);
			cidadeDao.deleteEntity(cidade);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
