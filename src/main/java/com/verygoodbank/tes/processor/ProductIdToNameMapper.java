package com.verygoodbank.tes.processor;

import org.springframework.stereotype.Service;


@Service
public class ProductIdToNameMapper
{
	public String map(String productId)
	{
		if ("1".equals(productId))
		{
			return "Product 1";
		}
		return "Missing Product Name";
	}
}
