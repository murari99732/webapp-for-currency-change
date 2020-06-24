package myfirstproject.exchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@Autowired
	Environment environment;
	
	@Autowired
	ExchangeRepository exchangeRepository;
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("/exchange/from/{from}/to/{to}")
	ExchangeValue retrieveValue(@PathVariable String from,@PathVariable String to)
	{
	
		
		ExchangeValue exchangeValue= exchangeRepository.findByFromAndTo(from, to);
		String d=environment.getProperty("local.server.port");
		System.out.println(d);
		
				return exchangeValue;
	}
	
	@Scheduled(cron = "${my.cron.expression}")
	@GetMapping("/exchange-from-web/from/{from}/to/{to}")
	ExchangeValue retrieveValueFromWeb(@PathVariable String from,@PathVariable String to) throws Exception
	{
		ExchangeValue exchange= new ExchangeValue();
		BigDecimal response=JsonToJava.convert_json_to_java(from, to);
	
		ExchangeValue exchangeValue= exchangeRepository.findByFromAndTo(from, to);
		if(exchangeValue!=null)
		{
		
			exchangeValue.setConversionMultiple(response);
			exchangeValue.setPort(Integer.parseInt(serverPort));
			return exchangeValue;
		}
		else
		{
			exchange.setId(exchangeRepository.getMaxId()+1);
			exchange.setFrom(from);
			exchange.setTo(to);
			exchange.setConversionMultiple(response);
			exchange.setPort(Integer.parseInt(serverPort));
			exchangeRepository.save(exchange);
			return exchange;
	
		}
	
	}
	

}
