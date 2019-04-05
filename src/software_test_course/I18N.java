package software_test_course;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class I18N {

	private final static String I18N_FILE = "..//conf//stringfilter-i18n.xml";
	private static Map<String, String> itemMap = new HashMap<String, String>();
	
	// 界面需要字符串资源时调用该方法
	public static String getI18nStr(String label) {
		return itemMap.get(label);
	}

	// 程序在显示界面时调用该方法
	public static void init() throws JDOMException, IOException {
		// 获取本地语言环境
		Locale currentLocale = Locale.getDefault();
		// 开始解析，解析结果放入itemMap中
		File file=new File(".//conf//stringfilter-i18n.xml");
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(file);
		Element root = doc.getRootElement(); // 获取根元素
		String language_country=currentLocale.getLanguage()+"_"+currentLocale.getCountry();
		List<Element> list = root.getChildren();
		for(Element ele : list) {
			if(ele.getAttributeValue("language-country").equals(language_country)) {
				List<Element> childElements = ele.getChildren();
				for(Element childele:childElements) {
					String attr1=childele.getAttributeValue("label-key").toString();
					String attr2=childele.getAttributeValue("label-value").toString();
					itemMap.put(attr1, attr2);
				}
			}
		}

	}

}
