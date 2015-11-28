/**
 * 
 */
package br.edu.unitri.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * @author marcos.fernando
 *
 */
public class RestUtil {

	/**
	 * Método para verificar a conexão com o Servidor JEE
	 * 
	 * @param base_uri
	 * @throws Exception
	 */
	public static void testConnection(String base_uri) throws Exception {
		try {
			URL url = new URL(base_uri);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();
		} catch (IOException e) {
			throw new Exception(
					"Erro na conexão: \nServidor não responde = " + e.getMessage() + ". \nFavor verificar a conexão!");
		}
	}

	/**
	 * Método que converte Entidade em JSON
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	protected static String entityToJson(Object entity) throws Exception {
		try {
			String jEntity = new ObjectMapper().writeValueAsString(entity);
			return URLEncoder.encode(jEntity, "UTF-8");
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * Método para converter JSON em entidade
	 * 
	 * @param entity
	 * @param json
	 * @return object
	 * @throws Exception
	 */
	protected static Object jsonToEntity(Object entity, String json) throws Exception {
		try {
			return entity.getClass().cast(new ObjectMapper().readValue(json, entity.getClass()));
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * Método que envia mensagem de Erro
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected static void handleError(String message) throws Exception {
		throw new Exception(message);
	}

	/**
	 * Método que envia mensagem de Erro Validation
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected static void handleErrorValidation(String message) throws Exception {
		throw new Exception(message);
	}

	/**
	 * Método que converte JSON array para ArraList<T>
	 * 
	 * @param json
	 * @param clazz
	 * @return List<T>
	 * @throws Exception
	 */
	protected static <T> List<T> mapJsonToObjectList(String json, Class<?> clazz) throws Exception {
		List<T> list = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory t = TypeFactory.defaultInstance();
		try {
			list = mapper.readValue(json, t.constructCollectionType(ArrayList.class, clazz));
		} catch (IOException e) {
			throw new Exception(e);
		}
		return list;
	}

}
