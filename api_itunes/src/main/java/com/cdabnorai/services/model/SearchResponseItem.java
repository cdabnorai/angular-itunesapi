package com.cdabnorai.services.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseItem implements Comparable<SearchResponseItem> {
	public String artistName = "";
	public String collectionName = "";
	public String trackName = "";
	
	@Override
	public int compareTo(SearchResponseItem o) {
		int c;
		c = this.artistName.compareTo(o.artistName);
		if(c == 0) {
			c = this.collectionName.compareTo(o.collectionName);
		}
		if(c == 0) {
			c = this.trackName.compareTo(o.trackName);
		}
		return c;
	}
}
