package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() 
	{
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The method getTotalSecondsGood is unsuccessful",seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad()
	{
		int seconds = Time.getTotalSeconds("10:00:00");
		assertThrows(NumberFormatException.class,()->{Time.getTotalSeconds("10:sd:23");});
	}

	@ParameterizedTest
	@ValueSource(strings= {"05:00:00","05:15:00","05:59:59"})
	void testGetTotalSecondsBoundary(String candidate)
	{
		int seconds = Time.getTotalSeconds(candidate);
		assertTrue("The time was not calculated properly",seconds!=22000);
	}
	
	@Test
	void testGetSecondsGood() 
	{
		int seconds = Time.getSeconds("10:00:50");
		assertTrue("The seconds were not calculated properly",seconds==50);
	}
	
	@Test
	void testGetSecondsBad() 
	{
		assertThrows(StringIndexOutOfBoundsException.class,()->{Time.getSeconds("10:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"01:10:10","10:01:10","11:11:10"})
	void testGetSecondsBoundary(String candidate)
	{
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not extracted properly",seconds==10);
	}

	@Test
	void testGetTotalMinutesGood() 
	{
		int minutes = Time.getTotalMinutes("23:59:59");
		assertTrue("The minutes were not extracted properly",minutes==59);
	}
	
	@Test
	void testGetTotalMinutesBad() 
	{
		assertThrows(NumberFormatException.class,()->{Time.getTotalSeconds("10:10:10:10:04");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"24:45:00","12:45:05","16:45:89"})
	void testGetTotalMinutesBoundary(String candidate)
	{
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly",minutes==45);
	}

	@Test
	void testGetTotalHoursGood() 
	{
		int hours = Time.getTotalHours("18:30:00");
		assertTrue("The hours were not calculated properly",hours==18);
	}
	
	@Test
	void testGetTotalHoursBad()
	{
		assertThrows(StringIndexOutOfBoundsException.class,()->{Time.getSeconds("10:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"10:10:10","10:30:00","10:45:00"})
	void testGetTotalHoursBoundary(String candidate)
	{
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours are not calculated properly",hours==10);
	}
	
	@Test
	void testGetMilliSecondsGood()
	{
		int milli = Time.getMilliSeconds("10:05:05:10");
		assertTrue("Milli seconds not calculated properly",milli==10);
	}
	
	@Test
	void testGetMilliSecondsBad()
	{
		assertThrows (NumberFormatException.class,()->{Time.getMilliSeconds("12:05:05:05:05");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"12:05:05:05","10:10:13:05","23:12:12:05"})
	void testGetMilliSecondsBoundary(String candidate)
	{
		int milli = Time.getMilliSeconds(candidate);
		assertTrue("Milli Seconds not calculated properly",milli==5);
	}

}
