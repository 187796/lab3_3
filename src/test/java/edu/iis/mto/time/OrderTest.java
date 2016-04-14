package edu.iis.mto.time;

import static org.junit.Assert.*;

import java.sql.Date;

import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.junit.Test;

public class OrderTest {

	private long invalidDate = DateTimeUtils.currentTimeMillis() + Duration.standardDays(1).getMillis()*2;
	
	@Test (expected = OrderExpiredException.class)
	public void confirmMethodShouldThrowOrderExpiredException() {
		DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis());
		Order order = new Order();
		
		
		order.submit();
		
		DateTimeUtils.setCurrentMillisFixed(invalidDate);
		
		order.confirm();
	}
	
	@Test
	public void confirmMethodShouldWorksFine(){
		DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis());
		Order order = new Order();
		
		order.submit();
		
		order.confirm();
		
		assertEquals(order.getOrderState(),Order.State.SUBMITTED);
	}

}
