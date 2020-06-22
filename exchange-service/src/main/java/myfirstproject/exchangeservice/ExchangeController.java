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
	
	@GetMapping("/exchange/from/{from}/to/{to}")
	ExchangeValue retrieveValue(@PathVariable String from,@PathVariable String to)
	{
		ExchangeValue exchangeValue= new ExchangeValue(1000L,from, to, BigDecimal.valueOf(65));
	//	exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
	//	environment.getProperty("local.server.port");
		String d=environment.getProperty("local.server.port");
		System.out.println(d);
		
				return exchangeValue;
	}

}
