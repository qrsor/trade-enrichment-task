package com.verygoodbank.tes.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;


@Service
public class LineProcessor
{
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	private Logger logger = LoggerFactory.getLogger(LineProcessor.class);
	private ProductIdToNameMapper mapper;

	public LineProcessor(ProductIdToNameMapper mapper)
	{
		this.mapper = mapper;
	}

	public Optional<String> process(String line) {
		var fields = line.split(",");

		var date = fields[0];
		var productId = fields[1];
		var currency = fields[2];
		var amount = fields[3];

		try
		{
			formatter.parse(date);
		}
		catch (DateTimeParseException e)
		{
			logger.warn("Date formatting failed on line {}", line);
			return Optional.empty();
		}

		var productName = mapper.map(productId);

		return "%s,%s,%s,%s".formatted(date, productName, currency, amount).describeConstable();
	}
}
