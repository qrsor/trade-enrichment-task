package com.verygoodbank.tes.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

record MemoryStats(long heapSize, long heapMaxSize, long heapFreeSize) {}

@RestController
class MonitoringController
{
	@GetMapping("memory-status")
	public MemoryStats getMemoryStatistics() {
		return new MemoryStats(Runtime.getRuntime().totalMemory(),Runtime.getRuntime().totalMemory(),Runtime.getRuntime().freeMemory());
	}

}
