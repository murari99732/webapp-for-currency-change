package conversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConversionController {
	
	@Autowired
	private Proxy proxy;
	
	@Autowired
	ConversionRepository conversionRepository;
	
	@GetMapping("/conversion/from/{from)/to/{to}/quantity/{quantity}")
public	CurrencyConversion getValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
	{


		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/exchange/from/{from}/to/{to}", CurrencyConversion.class,
				uriVariables);

		CurrencyConversion response = responseEntity.getBody();
		if(response==null)
		{
			return new CurrencyConversion(0L,"","",BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
		}

		return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()));
		
	}
	
	@GetMapping("/currency-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

		
		return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()));
	}

	@GetMapping("/currency-feign-web/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyWebFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion response = proxy.retrieveValueFromWeb(from, to);
	CurrencyConversion currencyConversion=	new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()));
		
	CurrencyConversion responseSave= new CurrencyConversion();
	responseSave.setId(response.getId());
	responseSave.setFrom(from);
	responseSave.setTo(to);
	responseSave.setConversionMultiple(response.getConversionMultiple());
	responseSave.setQuantity(quantity);
	responseSave.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
	if(conversionRepository.findById(responseSave.getId()) != null)
	{
	conversionRepository.save(responseSave);
	}
		return currencyConversion;
	}
	
	@GetMapping("/currency-feign-web/amount")
	public BigDecimal getAmmount()
	{
		return conversionRepository.selectTotals();
	}
	
}
