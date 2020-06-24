package myfirstproject.exchangeservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonToJava {

	public static BigDecimal convert_json_to_java(String from, String to) throws Exception {
		String fromUpper = from.toUpperCase();
		String toUpper = to.toUpperCase();
		String url = "https://www.xe.com/api/stats.php?fromCurrency=" + fromUpper + "&toCurrency=" + toUpper;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		BigDecimal resBigDecimal=extractedResponse(response);
		return resBigDecimal;

	}

	private static BigDecimal extractedResponse(StringBuffer response) {
		String responseToExtract = response.toString();

		String highVal = responseToExtract.substring(responseToExtract.indexOf("high") + 6,
				responseToExtract.indexOf("high") + 12);
		String lowVal =responseToExtract.substring(responseToExtract.indexOf("low") + 5,
				responseToExtract.indexOf("low") + 12);
	
		
		
		BigDecimal bigHigh= new BigDecimal(highVal);
		BigDecimal bigLow= new BigDecimal(lowVal);
	
		String val="2";
		BigDecimal div= new BigDecimal(val);
		BigDecimal sum=bigHigh.add(bigLow);
		BigDecimal average=sum.divide(div, RoundingMode.HALF_UP);
		return average;
	}
	
	
}
