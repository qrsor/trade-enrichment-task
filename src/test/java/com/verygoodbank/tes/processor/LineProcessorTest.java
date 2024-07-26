package com.verygoodbank.tes.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LineProcessorTest
{
	LineProcessor underTest = new LineProcessor(new ProductIdToNameMapper());

	@Test
	void shouldSkipLineWithInvalidDate()
	{
		//given
		String line = "20160140,1,EUR,10.0";

		//when
		var result = underTest.process(line);

		//then
		assertTrue(result.isEmpty());
	}

	@Test
	void shouldMapProductIdToName()
	{
		//given
		String line = "20160101,1,EUR,10.0";

		//when
		var result = underTest.process(line);

		//then
		assertTrue(result.isPresent());
		assertEquals("20160101,Treasury Bills Domestic,EUR,10.0", result.get());
	}

	@Test
	void shouldMapProductIdToPlaceholderIfNameIsMissingMapping()
	{
		//given
		String line = "20160101,1337,EUR,10.0";

		//when
		var result = underTest.process(line);

		//then
		assertTrue(result.isPresent());
		assertEquals("20160101,Missing Product Name,EUR,10.0", result.get());
	}
}