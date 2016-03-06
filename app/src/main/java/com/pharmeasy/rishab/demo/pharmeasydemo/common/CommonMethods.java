package com.pharmeasy.rishab.demo.pharmeasydemo.common;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class CommonMethods
{
      public static String performGet( String requestURL ) throws Exception
      {
            String                    responseBody = "";
            HttpClient                httpClient;
            HttpGet                   httpGet;
            ResponseHandler< String > responseHandler;
            HttpParams                httpParams;
            try
            {
                  httpParams = new BasicHttpParams();
                  HttpConnectionParams.setConnectionTimeout( httpParams, 2 * 60 * 1000 );// 2 min
                  HttpConnectionParams.setSoTimeout( httpParams, 2 * 60 * 1000 );// 2
                  httpClient = new DefaultHttpClient( httpParams );
                  httpGet = new HttpGet( requestURL );
                  responseHandler = new BasicResponseHandler();
                  responseBody = httpClient.execute( httpGet, responseHandler );
            }
            catch ( Exception ex )
            {
                  System.err.println( ex );
                  throw ex;
            }
            return responseBody;
      }
}
