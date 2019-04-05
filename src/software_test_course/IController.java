package software_test_course;

public interface IController {
	
	void setView(IView view);

	void addAvailFilterToFilterChain(IFilter initFilters);

	void removeFilterFromFilterChain(IFilter filter);

	void apply(String inputText);

	void removeFilterFromFilterChain(String filter);

	
}
