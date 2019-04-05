package software_test_course;

import java.io.IOException;

import org.jdom.JDOMException;

public class App {

//	private MockView view;
//	private Controller controller;
	private IController controller;
	private IView view;
//	private IFilter[] initFilters;
	public App(IFilter[] initFilters) {
		// TODO Auto-generated constructor stub
		controller = new Controller(initFilters);
//		view = new MockView(controller);
		view = new ViewSwingImpl(controller);
		controller.setView(view);
		((ViewSwingImpl) view).createView();
	}

	public Controller getController() {
		// TODO Auto-generated method stub
		return (Controller)controller;
	}

	public MockView getView() {
		// TODO Auto-generated method stub
		return (MockView)view;
	}

	public static void main(String args[]) throws JDOMException, IOException {
//		IFilter
		I18N.init();
		IFilter[] initFilters = new IFilter[] { new UpperCaseFilter(),
				new LowerCaseFilter(), new PrefixTrimFilter() };
		new App(initFilters);
	}
}
