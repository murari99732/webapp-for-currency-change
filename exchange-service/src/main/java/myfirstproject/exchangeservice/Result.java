package myfirstproject.exchangeservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	private String average;
	private String low;
	private String high;

	public Result() {
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	@Override
	public String toString() {
		return "Result [average=" + average + ", low=" + low + ", high=" + high + "]";
	}


}
