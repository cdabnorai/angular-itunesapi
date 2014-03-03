package com.cdabnorai.services.model;

public class SearchRequest {
	public String text;
	public SelectedMedia selectedMedia;
	
	public static class SelectedMedia {
		public String mediaName;
		public String mediaCode;
	}
}
