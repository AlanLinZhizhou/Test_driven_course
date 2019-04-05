package software_test_course;

public class LowerCaseFilter implements IFilter {

//	private String name = "小写转换器";
	private String name = I18N.getI18nStr("edu.jlxy.stringfilter.LOWERCASEFILTER");
//	private String name = I18N.getI18nStr(label)
	public LowerCaseFilter(String name) {
		this.name=name;
	}

	public LowerCaseFilter() {
//		this.name="小写转换器";
		this.name=I18N.getI18nStr("edu.jlxy.stringfilter.LOWERCASEFILTER");
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String filter(String text) {
		return text.toLowerCase();
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
		LowerCaseFilter other = (LowerCaseFilter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
