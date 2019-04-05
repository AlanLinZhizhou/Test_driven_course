package software_test_course;

public class UpperCaseFilter implements IFilter {

//	private String name = "´óÐ´×ª»»Æ÷";
	private String name = I18N.getI18nStr("edu.jlxy.stringfilter.UPPERCASEFILTER");
	public UpperCaseFilter(String name) {
		this.name = name;
	}

	public UpperCaseFilter() {
		this.name =  I18N.getI18nStr("edu.jlxy.stringfilter.UPPERCASEFILTER");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String filter(String text) {
		// TODO Auto-generated method stub
		return text.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpperCaseFilter other = (UpperCaseFilter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
