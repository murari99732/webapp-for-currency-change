package conversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConversionController {
	
	@GetMapping("convertor/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean convertCurrency(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity)
	{
		Map<String, String> uriVariables= new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<ConversionBean> responseEntity = new RestTemplate().getForEntity("localhost:8000/exchange/from/{from}/to/{to}", ConversionBean.class, uriVariables);
	
		ConversionBean response=responseEntity.getBody();
		
		return new ConversionBean(1L, from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), 8000);	
	}
}
