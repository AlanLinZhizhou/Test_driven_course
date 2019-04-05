package software_test_course;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private IFilter[] initFilters = new IFilter[] { new UpperCaseFilter("UPPERCASE_FILTER"),
			new LowerCaseFilter("LOWERCASE_FILTER"), new PrefixTrimFilter("TRIM_PREFIX_FILTER") };
	private App app;

	@Before
	public void setUp() {
		app = new App(initFilters);
	}

	@Test
	public void testAddAvailableFilterToFilterChain() {
		MockView view = app.getView();
		view.addAvailFilterToFilterChain(initFilters[0]);

//		List<String> filterChain = view.getFilterChain();
		IFilter[] availableFilters = view.getAvailableFilters();
		Assert.assertEquals(3, availableFilters.length);
		for (int i = 0; i < availableFilters.length; i++) {
			Assert.assertEquals(initFilters[i], availableFilters[i]);
		}

		// 验证用户所添加的“转换器”已经加入到“转换器链”中
		List<IFilter> filterChain = view.getFilterChain();
		Assert.assertEquals(1, filterChain.size());
		Assert.assertEquals(initFilters[0], filterChain.get(0));
		// 重复添加相同的“转换器”
		view.addAvailFilterToFilterChain(initFilters[0]);

		// 验证“可用转换器列表”中的“转换器”没有变化
		availableFilters = view.getAvailableFilters();
		Assert.assertEquals(3, availableFilters.length);
		for (int i = 0; i < availableFilters.length; i++) {
			Assert.assertEquals(initFilters[i], availableFilters[i]);
		}

		// 验证“转换器链”中仍然只有一个转换器，重复添加“转换器”无效
		filterChain = view.getFilterChain();
		Assert.assertEquals(1, filterChain.size());
		Assert.assertEquals(initFilters[0], filterChain.get(0));

		// 添加另外一个“转换器”
		view.addAvailFilterToFilterChain(initFilters[1]);

		// 验证“可用转换器列表”中的“转换器”没有变化
		availableFilters = view.getAvailableFilters();
		Assert.assertEquals(3, availableFilters.length);
		for (int i = 0; i < availableFilters.length; i++) {
			Assert.assertEquals(initFilters[i], availableFilters[i]);
		}

		// 验证用户所添加的“转换器”已经加入到“转换器链”中，并且添加的顺序是从头追加到尾部
		filterChain = view.getFilterChain();
		Assert.assertEquals(2, filterChain.size());
		Assert.assertEquals(initFilters[0], filterChain.get(0));
		Assert.assertEquals(initFilters[1], filterChain.get(1));
	}

	@Test
	public void testAppInitByController() {
		Controller controller = app.getController();

		IFilter[] initFilters = new IFilter[] { new UpperCaseFilter("UPPERCASE_FILTER"),
				new LowerCaseFilter("LOWERCASE_FILTER"),
				new PrefixTrimFilter("TRIM_PREFIX_FILTER") };

		IFilter[] availableFilters = controller.getAvailableFilters();
		for (int i = 0; i < initFilters.length; i++) {
			Assert.assertEquals(initFilters[i], availableFilters[i]);
		}
	}

	@Test
	public void testAppInitByMockView() {
		MockView mockView = app.getView();
		IFilter[] availableFilters = mockView.getAvailableFilters();
		for (int i = 0; i < initFilters.length; i++) {
			Assert.assertEquals(initFilters[i], availableFilters[i]);
		}
	}

	@Test
	public void testApplyFilterChain() {
		MockView view = app.getView();

		// 输入字符串"this is test text."
		view.input("this is test text.");

		// 添加小写转换大写的“转换器”
		view.addAvailFilterToFilterChain(initFilters[0]);

		// 应用转换逻辑
		view.apply();

		// 获取输出字符串
		String output = view.getOutput();

		// 验证小写已经转换为大写
		Assert.assertEquals("THIS IS TEST TEXT.", output);
	}

	@Test
	public void testApplyFilterChain_2() {
		MockView view = app.getView();

		// 输入字符串"this is test text."
		view.input("this is test text.");

		// 添加小写转换大写的“转换器”
		view.addAvailFilterToFilterChain(initFilters[0]);

		// 添加大写转换小写的“转换器”
		view.addAvailFilterToFilterChain(initFilters[1]);

		// 应用转换逻辑
		view.apply();

		// 获取输出字符串
		String output = view.getOutput();

		// 验证小写已经转换为大写
		Assert.assertEquals("this is test text.", output);
	}

}
