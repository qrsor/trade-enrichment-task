package com.verygoodbank.tes.processor;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LineProcessor
{
	public Optional<String> process(String line) {
		return Optional.of(line);
	}
}
