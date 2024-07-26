package com.verygoodbank.tes.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


@Service
public class ProductIdToNameMapper
{
	private final Map<String, String> csvData = new HashMap<>();

	public ProductIdToNameMapper(@Value("${product.name.file}") String productNameFile)
	{
		try (InputStream is = new ClassPathResource(productNameFile).getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is)))
		{

			String line;
			while ((line = br.readLine()) != null)
			{
				String[] values = line.split(",");
				if (values.length >= 2)
				{
					csvData.put(values[0], values[1]);
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException("Failed to read product.csv", e);
		}
	}

	@Cacheable("productNames")
	public String map(String productId)
	{
		return csvData.getOrDefault(productId, "Missing Product Name");
	}
}
