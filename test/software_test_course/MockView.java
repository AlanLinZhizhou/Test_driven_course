package software_test_course;

import java.util.ArrayList;
import java.util.List;

public class MockView implements IView {
	private IFilter[] availableFilters;
	private IController controller;
	private List<IFilter> filterChain;
	private String inputText;
	private String outputText;

	public IFilter[] getAvailableFilters() {
		// TODO Auto-generated method stub
		return availableFilters;
	}

	public MockView(IController controller) {
		this.controller = controller;
		filterChain = new ArrayList<IFilter>();
	}

	public void updateAvailableFilters(IFilter[] availableFilters) {
		// TODO Auto-generated method stub
		this.availableFilters = availableFilters;
	}

	public void addAvailFilterToFilterChain(IFilter initFilters) {
		// TODO Auto-generated method stub
		controller.addAvailFilterToFilterChain(initFilters);

	}
	public List<IFilter> getFilterChain() {
		return filterChain;
	}

	@Override
	public void updateFilterChain(List<IFilter> filterChain) {
		this.filterChain = filterChain;
	}
	
	public void removeFilterFromFilterChain(IFilter filter) {
		controller.removeFilterFromFilterChain(filter);
	}

	public void input(String inputText) {
		this.inputText = inputText;
	}

	public void apply() {
		controller.apply(this.inputText);
	}

	@Override
	public void updateOutput(String outputText) {
		this.outputText = outputText;
	}

	public String getOutput() {
		return outputText;
	}



}
