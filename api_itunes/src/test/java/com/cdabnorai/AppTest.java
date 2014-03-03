package com.cdabnorai;

import org.apache.log4j.Logger;

import com.cdabnorai.services.ItunesSearchService;
import com.cdabnorai.services.model.SearchRequest;
import com.cdabnorai.services.model.SearchResponse;
import com.cdabnorai.services.model.SearchResponseItem;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	Logger logger = Logger.getLogger(this.getClass());
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        ItunesSearchService service = new ItunesSearchService();
        try {
        	SearchRequest request = new SearchRequest();
        	request.text = "Skrillex";
        	SearchRequest.SelectedMedia media = new SearchRequest.SelectedMedia();
        	media.mediaName = "Music";
        	media.mediaCode = "music";
        	request.selectedMedia = media;
        	
        	SearchResponse response = service.performSearch(request);
        	logger.info(response.resultCount);
        	for(SearchResponseItem item : response.results) {
        		logger.info("artistName: " + item.artistName);
        		logger.info("collectionName: " + item.collectionName);
        		logger.info("trackName: " + item.trackName);
        		logger.info("");
        	}
        	
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
    }
}
