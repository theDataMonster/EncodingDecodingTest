package com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;

import org.junit.Test;

public class URLEncodingTests {
	
	protected static String testUrl="http://www.abc.com?key1=value+1&key2=value%40%21%242&key3=value%253";
	
	
	
	private static String encodeValue(String value) 
	{
		String encoded =null;
		
		try {
			encoded= URLEncoder.encode(value,StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encoded;
	}
	
	private static String decode(String value) throws UnsupportedEncodingException
	{
		return URLDecoder.decode(value,StandardCharsets.UTF_8.toString());
	}
	
	@Test
	public void doTests_url_analyze()
	{
		
		
		try {
			URI uri=new URI(testUrl);
			
			
			assertThat(uri.getScheme(),is("http"));
			assertThat(uri.getHost(), is("www.abc.com"));
			assertThat(uri.getRawQuery(),
					is("key1=value+1&key2=value%40%21%242&key3=value%253"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void doTests_encode_givenRequestParams()
	{
		Map<String,String> requestParams=new HashMap<>();
		requestParams.put("key1", "value1");
		requestParams.put("key2", "value@!$2");
		requestParams.put("key3", "value%3");
		
		String encodedUrl=requestParams.keySet().stream().map(key->key+"="+encodeValue(requestParams.get(key))).collect(joining("&","http://www.abc.com?",""));
	}
	
	@Test
	public void doDecodeTest()
	{
		try {
			URI uri=new URI(testUrl);
			
			String scheme=uri.getScheme();
			String host=uri.getHost();
			String query=uri.getRawQuery();
			
			String decodedQuery=Arrays.stream(query.split("&")).map(param->param.split("=")[0]+decode(param.split("=")[1])).collect(Collectors.joining("&"));
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
