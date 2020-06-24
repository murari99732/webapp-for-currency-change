package myfirstproject.exchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeRepository extends JpaRepository<ExchangeValue,Long> {
	
	ExchangeValue findByFromAndTo(String from, String to);
	
	@Query(value = "SELECT MAX(id) FROM exchange_value", nativeQuery = true)
	Long getMaxId();

}
