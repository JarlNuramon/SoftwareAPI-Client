package de.hs.bochum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hs.bochum.buisness.messung.Messreihe;
import de.hs.bochum.buisness.messung.MessreiheResponse;
import de.hs.bochum.buisness.messung.CreateMessungRequest;

public class Model {

	private Model() {

	}

	private static Model instance = null;
	private static HttpClient httpClient; 
	private static ObjectMapper objectMapper;
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
			httpClient = HttpClientBuilder.create().build();
			objectMapper = new ObjectMapper();
		}
		return instance;

	}
	

	public void startMessung(int id) throws Exception {
		HttpPost post = new HttpPost("http://localhost:8080/api/v1/messung/start/"+id);
		HttpResponse response = httpClient.execute(post);
		if (response.getStatusLine().getStatusCode() != 200)
			throw new Exception("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
	}

	public Messreihe stopMessung(int id) throws Exception {
		HttpPost post = new HttpPost("http://localhost:8080/api/v1/messung/stop/"+id);
		HttpResponse response = httpClient.execute(post);
		if (response.getStatusLine().getStatusCode() != 200)
			throw new Exception("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		return unmarshallMessreihe(response);
	
	}

	public void erstelleMessreihe(int id, int zeitIntervall, String verbraucher, String messgroesse) throws Exception {
		HttpPost post = new HttpPost("http://localhost:8080/api/v1/messung/create");
		StringEntity entity = new StringEntity(objectMapper.writerFor(CreateMessungRequest.class)
				.writeValueAsString(CreateMessungRequest.builder().id(id).zeitIntervall(zeitIntervall).verbraucher(verbraucher).messgroesse(messgroesse).build()));
		post.setEntity(entity);
		post.setHeader("Content-type", "application/json");
		HttpResponse response = httpClient.execute(post);
		if (response.getStatusLine().getStatusCode() != 200)
			throw new Exception("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		
	}

	public List<Messreihe> getMessungen() throws Exception {
		 HttpGet getRequest = new HttpGet(
			        "http://localhost:8080/api/v1/messung");
			    HttpResponse response = httpClient.execute(getRequest);
			    if (response.getStatusLine().getStatusCode() != 200) 
			        throw new Exception("Failed : HTTP error code : "
			           + response.getStatusLine().getStatusCode());
			    
		return objectMapper.readValue(response.getEntity().getContent(),MessreiheResponse.class).getMessungen();
		
	}

	private Messreihe unmarshallMessreihe(HttpResponse response)
			throws IOException, JsonParseException, JsonMappingException {
		return  objectMapper.readValue(response.getEntity().getContent(),Messreihe.class);
	}

	

	
	
}
