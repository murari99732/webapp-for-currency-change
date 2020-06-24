package conversionservice;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="exchange-service", url="localhost:8000")


public interface Proxy {

	@GetMapping("/exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue
		(@PathVariable("from") String from, @PathVariable("to") String to);
	

	@GetMapping("/exchange-from-web/from/{from}/to/{to}")
	public CurrencyConversion retrieveValueFromWeb(@PathVariable("from") String from, @PathVariable("to") String to );
}
