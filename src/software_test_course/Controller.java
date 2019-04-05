package software_test_course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller implements IController{
	
	private IView view;
	private IFilter[] availableFilters;
	List<IFilter> filterChain;
	public Controller(IFilter[] initFilters) {
		// TODO Auto-generated constructor stub
		availableFilters = initFilters;
		filterChain=new ArrayList<IFilter>();
	}

	@Override
	public void apply(String inputText) {

		String output = "";

		// TODO 对inputText按照“转换器链”顺序进行处理，并将处理逻辑赋给output
		for (Iterator<IFilter> iterator = filterChain.iterator(); iterator
				.hasNext();) {
			IFilter filter = iterator.next();
			inputText = filter.filter(inputText);
		}

		output = inputText;

		this.view.updateOutput(output);

	}

	@Override
	public void removeFilterFromFilterChain(String filter) {
		filterChain.remove(filter);
		this.view.updateFilterChain(filterChain);
	}

	public IFilter[] getAvailableFilters() {
		// TODO Auto-generated method stub
		return availableFilters;
	}

	public void setView(IView view) {
		// TODO Auto-generated method stub
		this.view=view;
		this.view.updateAvailableFilters(availableFilters);
	}
	
	public IView getView() {
		return view;
	}
	@Override
	public void addAvailFilterToFilterChain(IFilter filter) {
		if (!filterChain.contains(filter)) {
			filterChain.add(filter);
			this.view.updateFilterChain(filterChain);
		}
	}

	@Override
	public void removeFilterFromFilterChain(IFilter filter) {
		// TODO Auto-generated method stub
		filterChain.remove(filter);
		this.view.updateFilterChain(filterChain);

	}




}
