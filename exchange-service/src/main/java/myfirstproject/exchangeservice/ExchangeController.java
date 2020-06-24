package myfirstproject.exchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@Autowired
	Environment environment;
	
	@Autowired
	ExchangeRepository exchangeRepository;
	
	@GetMapping("/exchange/from/{from}/to/{to}")
	ExchangeValue retrieveValue(@PathVariable String from,@PathVariable String to)
	{
	
		
		ExchangeValue exchangeValue= exchangeRepository.findByFromAndTo(from, to);
		String d=environment.getProperty("local.server.port");
		System.out.println(d);
		
				return exchangeValue;
	}
	
	@GetMapping("/exchange-from-web/from/{from}/to/{to}")
	BigDecimal retrieveValueFromWeb(@PathVariable String from,@PathVariable String to) throws Exception
	{
	
		BigDecimal response=JsonToJava.convert_json_to_java(from, to);
		return response;
	}
	

}
