package conversionservice;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConversionRepository extends CrudRepository<CurrencyConversion, Long> {
	@Query(value = "SELECT SUM(Total_Calculated_Amount) FROM Currency_Conversion", nativeQuery = true)
	 BigDecimal selectTotals ();

}
