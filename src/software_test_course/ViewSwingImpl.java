package software_test_course;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.ScrollPaneConstants;

public class ViewSwingImpl implements IView {

	private final IController controller;

	public ViewSwingImpl(final IController controller) {
		this.controller = controller;
	}

	public void createView() {
		initView();
		initAction();
		frame.setVisible(true);
	}

	private void initView() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(200, 100, 600, 400);

		frame.setMinimumSize(new Dimension(600, 400));

//		frame.setTitle("字符串变换器");
		frame.setTitle(I18N.getI18nStr("edu.jlxy.stringfilter.STRINGFILTER"));

		frame.setLayout(new BorderLayout());

		JPanel resultPanel = new JPanel(new GridBagLayout());

//		resultPanel.add(new JLabel("源字符串:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST,
//				GridBagConstraints.BOTH, new Insets(20, 20, 0, 10), 0, 0));

		resultPanel.add(new JLabel(I18N.getI18nStr("edu.jlxy.stringfilter.SOURCESTRING")), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(20, 20, 0, 10), 0, 0));

		
		resultPanel.add(srcString, new GridBagConstraints(1, 0, 2, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(20, 0, 0, 20), 0, 0));
//		resultPanel.add(new JLabel("变换结果:"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
//				GridBagConstraints.BOTH, new Insets(10, 20, 0, 10), 0, 0));

		resultPanel.add(new JLabel(I18N.getI18nStr("edu.jlxy.stringfilter.STRINGFILTERED")), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 20, 0, 10), 0, 0));
		
		dstString.setEditable(false);

		resultPanel.add(dstString, new GridBagConstraints(1, 1, 2, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(10, 0, 0, 20), 0, 0));

		frame.add(resultPanel, BorderLayout.NORTH);

		JPanel filterPanel = new JPanel(new GridBagLayout());

//		filterPanel.add(new JLabel("可用变换器"), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
//				GridBagConstraints.VERTICAL, new Insets(10, 20, 0, 20), 0, 0));

		filterPanel.add(new JLabel(I18N.getI18nStr("edu.jlxy.stringfilter.AVAILABLEFILTER")), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.VERTICAL, new Insets(10, 20, 0, 20), 0, 0));

		
		JScrollPane scrollPane = new JScrollPane(availableFilterList);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		filterPanel.add(scrollPane, new GridBagConstraints(0, 1, 1, 4, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 20, 0, 20), 0, 0));

		filterPanel.add(addFilterButton, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(20, 20, 0, 10), 0, 0));

		filterPanel.add(removeFilterButton, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(20, 20, 0, 10), 0, 0));

		filterPanel.add(removeAllFilterButton, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(20, 20, 0, 10), 0, 0));

//		filterPanel.add(new JLabel("变换器链"), new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
//				GridBagConstraints.VERTICAL, new Insets(10, 20, 0, 20), 0, 0));

		filterPanel.add(new JLabel(I18N.getI18nStr("edu.jlxy.stringfilter.FILTERCHAIN")), new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.VERTICAL, new Insets(10, 20, 0, 20), 0, 0));

		
		filterPanel.add(new JScrollPane(selectedFilterList), new GridBagConstraints(2, 1, 1, 4, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 0, 20), 0, 0));

		frame.add(filterPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.add(applyButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END,
				GridBagConstraints.BOTH, new Insets(20, 20, 20, 10), 0, 0));
		buttonPanel.add(quitButton, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END,
				GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
		JPanel p = new JPanel(new BorderLayout());
		p.add(buttonPanel, BorderLayout.EAST);
		frame.add(p, BorderLayout.SOUTH);
	}

	private void initAction() {
		addFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				controller.addAvailFilterToFilterChain((IFilter) availableFilterList.getSelectedValue());
			}
		});

		removeFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				controller.removeFilterFromFilterChain((IFilter) selectedFilterList.getSelectedValue());
			}
		});

		removeAllFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ListModel<IFilter> model = selectedFilterList.getModel();
				int count = model.getSize();
				for (int i = 0; i < count; i++) {
					IFilter filter = (IFilter) selectedFilterList.getModel().getElementAt(0);
					controller.removeFilterFromFilterChain(filter);
				}
			}
		});

		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				controller.apply(srcString.getText());
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private final JFrame frame = new JFrame();

	private final JTextField srcString = new JTextField();

	private final JTextField dstString = new JTextField();

	private final JList<IFilter> availableFilterList = new JList<IFilter>();

	private final JList<IFilter> selectedFilterList = new JList<IFilter>();

//	private final JButton addFilterButton = new JButton("添加 >");

	private final JButton addFilterButton = new JButton(I18N.getI18nStr("edu.jlxy.stringfilter.ADD"));
	
//	private final JButton removeFilterButton = new JButton("< 去除");
	private final JButton removeFilterButton = new JButton(I18N.getI18nStr("edu.jlxy.stringfilter.REMOVE"));

//	private final JButton removeAllFilterButton = new JButton("<<去除所有");
	private final JButton removeAllFilterButton = new JButton(I18N.getI18nStr("edu.jlxy.stringfilter.REMOVEALL"));

//	private final JButton applyButton = new JButton("应用");
	private final JButton applyButton = new JButton(I18N.getI18nStr("edu.jlxy.stringfilter.APPLY"));

//	private final JButton quitButton = new JButton("取消");
	private final JButton quitButton = new JButton(I18N.getI18nStr("edu.jlxy.stringfilter.EXIT"));

	public void updateAvailaleFilters(final IFilter[] availableFilters) {
		MutableComboBoxModel<IFilter> model = new DefaultComboBoxModel<IFilter>();
		for (int i = 0; i < availableFilters.length; i++) {
			IFilter filter = availableFilters[i];
			model.addElement(filter);
		}
		availableFilterList.setModel(model);
	}

	public void updateFilterChain(final List<IFilter> filterChain) {
		MutableComboBoxModel<IFilter> model = new DefaultComboBoxModel<IFilter>();
		for (int i = 0; i < filterChain.size(); i++) {
			IFilter filter = filterChain.get(i);
			model.addElement(filter);
		}
		selectedFilterList.setModel(model);
	}

	public void updateOutput(final String output) {
		dstString.setText(output);
	}

	@Override
	public void updateAvailableFilters(IFilter[] availableFilters) {
		// TODO Auto-generated method stub

		MutableComboBoxModel model = new DefaultComboBoxModel();
		for (int i = 0; i < availableFilters.length; i++) {
			IFilter filter = availableFilters[i];
			model.addElement(filter);
		}
		availableFilterList.setModel(model);

	}
}
