package conversionservice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CurrencyConversionMapper implements RowMapper<CurrencyConversion> {

	@Override
	public CurrencyConversion mapRow(ResultSet rs, int rowNum) throws SQLException {
		CurrencyConversion currencyConversion= new CurrencyConversion();
		currencyConversion.setId(rs.getLong("id"));
		currencyConversion.setFrom(rs.getString("conv_from"));
		currencyConversion.setTo(rs.getString("conv_to"));
		currencyConversion.setPort(Integer.parseInt(rs.getString("port")));
		currencyConversion.setTotalCalculatedAmount(rs.getBigDecimal("total_calculated_amount"));
		currencyConversion.setQuantity(rs.getBigDecimal("quantity"));
		currencyConversion.setConversionMultiple(rs.getBigDecimal("conversion_multiple"));
		
		return currencyConversion;
	}

}
