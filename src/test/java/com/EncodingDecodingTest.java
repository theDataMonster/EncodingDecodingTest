package com;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class EncodingDecodingTest {
	
	public static String decodeText(String input, String encoding) throws IOException
	{
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes()), Charset.forName(encoding))).readLine();
	}
	
	public static String convertToBinary(String input, String encoding)
	{
		byte[] encoded_input=Charset.forName(encoding).encode(input).array();
		return IntStream.range(0, encoded_input.length).map(i->encoded_input[i]).mapToObj(e->Integer.toBinaryString(e^255)).
				map(e->String.format("%1$"+Byte.SIZE+"s", e).replace(" ","0")).collect(Collectors.joining(" "));
	}
	
	@Test
	public void runTests()
	{
//		assertEquals(convertToBinary("T","US-ASCII"), "01010100");
//		assertEquals(convertToBinary("語", "Big5"), "10111011 01111001");
//		assertEquals(convertToBinary("T", "UTF-32"), "00000000 00000000 00000000 01010100");
		assertEquals(Charset.defaultCharset().displayName(),"windows-1252");
		
	}

}
