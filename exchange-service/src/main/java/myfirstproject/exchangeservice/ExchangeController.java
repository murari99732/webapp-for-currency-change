package myfirstproject.exchangeservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@GetMapping("/exchange/from/{from}/to/{to}")
	ExchangeValue retrieveValue(@PathVariable String from,@PathVariable String to)
	{
		return new ExchangeValue(1000L,from, to, BigDecimal.valueOf(65));
	}

}
