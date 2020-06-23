package myfirstproject.exchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeValue,Long> {
	
	ExchangeValue findByFromAndTo(String from, String to);

}
