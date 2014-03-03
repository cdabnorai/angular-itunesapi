package com.cdabnorai.services;

import java.net.URLEncoder;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.cdabnorai.services.model.SearchRequest;
import com.cdabnorai.services.model.SearchResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Path("/api/itunes")
public class ItunesSearchService {
	Logger logger = Logger.getLogger(this.getClass());
	
	public static String SERVICE = "http://itunes.apple.com/search";
	public static int RESULTS_LIMIT = 200;

	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResponse performSearch(SearchRequest request) throws Exception{
		String term = request.text;
		term = URLEncoder.encode(term, "UTF-8");
		String media = request.selectedMedia.mediaCode;
		media = URLEncoder.encode(media, "UTF-8");
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(clientConfig);

		WebResource webResource = client
				.resource(SERVICE + "?limit=" + RESULTS_LIMIT + "&term=" + term + "&media=" + media);

		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		ObjectMapper mapper = new ObjectMapper();
		SearchResponse searchResponse = mapper.readValue(output, SearchResponse.class);
		Collections.sort(searchResponse.results);
		return searchResponse;
	}

}
