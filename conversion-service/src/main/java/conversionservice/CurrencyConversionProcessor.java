package conversionservice;

import org.springframework.batch.item.ItemProcessor;

public class CurrencyConversionProcessor implements ItemProcessor<CurrencyConversion,CurrencyConversion> {

	@Override
	public CurrencyConversion process(CurrencyConversion item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
