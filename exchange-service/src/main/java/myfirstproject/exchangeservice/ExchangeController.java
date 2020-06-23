package myfirstproject.exchangeservice;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ExchangeController {

	@Autowired
	Environment environment;

	@Autowired
	ExchangeRepository exchangeRepository;

	@Autowired
	RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);

	@GetMapping("/exchangefromweb/{from}/to{to}")

	Response getValueFromWeb(@PathVariable String from, @PathVariable String to) {
		ResponseEntity<Response> response = extracted(from, to);

		log.info("Resposne to see", response.toString());

		return response.getBody();

	}

	private ResponseEntity<Response> extracted(String from, String to) {
		String url = "https://www.xe.com/api/stats.php?fromCurrency=" + from + "&toCurrency=" + to;
		// I am using https://www.xe.com/api/stats.php?fromCurrency=USD&toCurrency=INR
		// url

		ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);

		log.info("Resposne to see", response.toString());
		System.out.println("..........................................." + response);
		if (response.getStatusCode().toString()!="400") {
			log.info("failed");

		}
		return response;
	}

	@GetMapping("/exchange/from/{from}/to/{to}")
	ExchangeValue retrieveValue(@PathVariable String from, @PathVariable String to) {

		// exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		// environment.getProperty("local.server.port");

		ExchangeValue exchangeValue = exchangeRepository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
		String d = environment.getProperty("local.server.port");
		System.out.println(d);

		return exchangeValue;
	}
	


}
