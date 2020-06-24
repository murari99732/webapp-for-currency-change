package conversionservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class JsonObjectToJava {
    public static void main(String[] args)throws Exception  {
        try {
        	JsonObjectToJava.convert_json_to_java();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void convert_json_to_java() throws Exception {
        String from = "USD";
        String to = "INR";
        String url = "https://www.xe.com/api/stats.php?fromCurrency="+from+"&toCurrency="+to ;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
        } in .close();
        String responseToExtract=response.toString();
        
        Integer highVal= Integer.parseInt(responseToExtract.substring(responseToExtract.indexOf("high")+6,responseToExtract.indexOf("high" )+12));
        Integer lowVal=Integer.parseInt(responseToExtract.substring(responseToExtract.indexOf("low")+6,responseToExtract.indexOf("low" )+12));
        Integer averageVal=Integer.parseInt(responseToExtract.substring(responseToExtract.indexOf("low")+6,responseToExtract.indexOf("low" )+12));
      


        
        
}
}

