package com.verygoodbank.tes.processor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


@Service
public class ProductIdToNameMapper
{
	private Map<String, String> csvData = new HashMap<>();

	public ProductIdToNameMapper()
	{
		try (InputStream is = new ClassPathResource("product.csv").getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length >= 2) {
					csvData.put(values[0], values[1]);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to read product.csv", e);
		}
	}

	public String map(String productId)
	{
		return csvData.getOrDefault(productId, "Missing Product Name");
	}
}
