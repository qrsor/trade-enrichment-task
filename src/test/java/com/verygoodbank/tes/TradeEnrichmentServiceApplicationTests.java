package com.verygoodbank.tes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = TradeEnrichmentServiceApplication.class)
@AutoConfigureMockMvc
class TradeEnrichmentServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void enrichShouldReturnSampleFileWithoutError() throws Exception {
		var tradeCsv = new ClassPathResource("trade.csv").getInputStream();
		MockMultipartFile file = new MockMultipartFile("file", tradeCsv);

		mvc.perform(MockMvcRequestBuilders.multipart("/api/v1/enrich").file(file).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_OCTET_STREAM));
	}
}
