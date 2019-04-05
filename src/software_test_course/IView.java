package software_test_course;

import java.util.List;

public interface IView {
	
	void updateAvailableFilters(IFilter[] availableFilters);


	void updateOutput(String output);


	void updateFilterChain(List<IFilter> filterChain);



	
	
}
