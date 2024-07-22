package com.verygoodbank.tes.web.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@RestController
@RequestMapping("api/v1")
public class TradeEnrichmentController
{

	Logger logger = LoggerFactory.getLogger(TradeEnrichmentController.class);

	@RequestMapping(value = "/enrich", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void enrich(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException
	{
		try (var inputStream = file.getInputStream();
				var reader = new BufferedReader(new InputStreamReader(inputStream));
				var outStream = response.getOutputStream();)
		{
			String line;
			reader.readLine(); //skip 1
			outStream.write("date,product_name,currency,price\n".getBytes());
			while ((line = reader.readLine()) != null)
			{
				outStream.write(processLine(line).getBytes());
				outStream.write('\n');
			}
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private String processLine(String line)
	{
		return line;
	}

}


