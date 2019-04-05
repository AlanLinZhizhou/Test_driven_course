package software_test_course;

import org.junit.Test;
import org.junit.Assert;

public class FilterTest {
	@Test
	public void testFilter() {
		IFilter upperCaseFilter = new UpperCaseFilter();

		Assert.assertEquals("大写转换器", upperCaseFilter.getName());

		Assert.assertEquals("ABCD", upperCaseFilter.filter("abcd"));

		IFilter anotherUpperCaseFilter = new UpperCaseFilter();

		Assert.assertEquals(anotherUpperCaseFilter, upperCaseFilter);
	}

	@Test
	public void testUpperCaseFilter() {
		IFilter upperCaseFilter = new UpperCaseFilter();

		Assert.assertEquals("大写转换器", upperCaseFilter.getName());

		Assert.assertEquals("ABCD", upperCaseFilter.filter("abcd"));

		IFilter anotherUpperCaseFilter = new UpperCaseFilter();

		Assert.assertEquals(anotherUpperCaseFilter, upperCaseFilter);
		//test name
		Assert.assertEquals("大写转换器", upperCaseFilter.toString());
	}

	@Test
	public void testLowerCaseFilter() {
		IFilter lowerCaseFilter = new LowerCaseFilter();

		Assert.assertEquals("小写转换器", lowerCaseFilter.getName());

		Assert.assertEquals("abcd", lowerCaseFilter.filter("ABCD"));

		IFilter anotherLowerCaseFilter = new LowerCaseFilter();

		Assert.assertEquals(anotherLowerCaseFilter, lowerCaseFilter);
		
		Assert.assertEquals("小写转换器", lowerCaseFilter.toString());
	}

	@Test
	public void testPrefixTrimFilter() {
		IFilter prefixTrimFilter = new PrefixTrimFilter();

		Assert.assertEquals("去除前缀空格转换器", prefixTrimFilter.getName());

		Assert.assertEquals("ABCD", prefixTrimFilter.filter("  ABCD"));

		IFilter anotherPrefixTrimFilter = new PrefixTrimFilter();

		Assert.assertEquals(anotherPrefixTrimFilter, prefixTrimFilter);
		
		Assert.assertEquals("去除前缀空格转换器", prefixTrimFilter.toString());
	}

}
