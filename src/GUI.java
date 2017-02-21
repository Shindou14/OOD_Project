import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel;

//界面类
public class GUI extends JFrame
{
	// 所有组件声明
	private JTextField inputField = new JTextField();
	private JButton searchButton = new JButton("搜索");
	private JComboBox<String> transOption = new JComboBox<>(new String[]
	{ "英->汉", "汉->英" });
	// 状态模式
	boolean transOpt = false;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList<String> associatedWordsList = new JList<>(listModel);
	JScrollPane associatedWords;
	private JTextArea localTransArea = new JTextArea();
	private JTextArea baiduTransArea = new JTextArea();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu personalize = new JMenu("个性化");
	private JMenu help = new JMenu("帮助");
	private JMenu theme = new JMenu("主题");
	private JMenu font = new JMenu("字体");
	private JRadioButtonMenuItem theme1, theme2, theme3, font1, font2, font3;
	private JMenuItem changeFontSize = new JMenuItem("大小");
	private JMenuItem about = new JMenuItem("关于");
	private JMenuItem restoreDefault = new JMenuItem("默认");
	private WordProcessorBridge enWordProcessor;
	private WordProcessorBridge chWordProcessor;

	// 生成两个字典的构造器
	public GUI() throws Exception
	{
		enWordProcessor = new WordProcessorBridge("en");
		chWordProcessor = new WordProcessorBridge("ch");

	}

	public static void main(String[] args) throws Exception
	{
		new GUI().initialize();
	}

	// 统一设置组件位置
	private void setComponentsLocation()
	{
		transOption.setBounds(10, 10, 80, 30);
		inputField.setBounds(90, 10, 320, 30);
		searchButton.setBounds(410, 10, 80, 30);
		associatedWords.setBounds(10, 50, 150, 300);
		localTransArea.setBounds(170, 50, 320, 150);
		baiduTransArea.setBounds(170, 200, 320, 150);
	}

	// 统一设置组件属性
	private void setComponentsAttribution()
	{
		associatedWords = new JScrollPane(associatedWordsList);
		localTransArea.setBorder(new TitledBorder("本地翻译"));
		baiduTransArea.setBorder(new TitledBorder("百度翻译"));
		localTransArea.setEditable(false);
		baiduTransArea.setEditable(false);
		localTransArea.setLineWrap(true);
		baiduTransArea.setLineWrap(true);
		associatedWordsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// 统一添加组件
	private void addAllComponents()
	{
		getContentPane().add(inputField);
		getContentPane().add(transOption);
		getContentPane().add(searchButton);
		getContentPane().add(associatedWords);
		getContentPane().add(localTransArea);
		getContentPane().add(baiduTransArea);
		menuBar.add(personalize);
		menuBar.add(help);
		personalize.add(theme);
		personalize.add(font);
		personalize.add(restoreDefault);
		ButtonGroup buttonGroup = new ButtonGroup();
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup.add(theme1 = new JRadioButtonMenuItem("主题1"));
		buttonGroup.add(theme2 = new JRadioButtonMenuItem("主题2"));
		buttonGroup.add(theme3 = new JRadioButtonMenuItem("主题3"));
		buttonGroup2.add(font1 = new JRadioButtonMenuItem("黑体"));
		buttonGroup2.add(font2 = new JRadioButtonMenuItem("宋体"));
		buttonGroup2.add(font3 = new JRadioButtonMenuItem("楷体"));
		theme.add(theme1);
		theme.add(theme2);
		theme.add(theme3);
		font.add(font1);
		font.add(font2);
		font.add(font3);
		font.add(changeFontSize);
		help.add(about);
	}

	// 统一修改字体
	private void changeFont(Font font)
	{
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}
		personalize.setFont(font);
		help.setFont(font);
		theme.setFont(font);
		this.font.setFont(font);
		transOption.setFont(font);
		searchButton.setFont(font);
		help.setFont(font);
		about.setFont(font);
		associatedWordsList.setFont(font);
		changeFontSize.setFont(font);
		restoreDefault.setFont(font);
		theme1.setFont(font);
		theme2.setFont(font);
		theme3.setFont(font);
		font1.setFont(font);
		font2.setFont(font);
		font3.setFont(font);
		localTransArea.setFont(font);
		baiduTransArea.setFont(font);
		inputField.setFont(font);
	}

	// 更换主题
	private void changeTheme(int option)
	{
		try
		{
			switch (option)
			{
			case 0:
				UIManager.setLookAndFeel(new MetalLookAndFeel());
				localTransArea.setBorder(new TitledBorder("本地翻译"));
				baiduTransArea.setBorder(new TitledBorder("百度翻译"));
				break;
			case 1:
				UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
				break;
			case 2:
				UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
				break;
			case 3:
				UIManager.setLookAndFeel(new SubstanceFieldOfWheatLookAndFeel());
				break;
			}
			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					SwingUtilities.updateComponentTreeUI(GUI.this);
				}
			});
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 搜索单词
	private void search(String word)
	{
		baiduTransArea.setText(transOpt ? GetBaiduTrans.getZhToEnTrans(word) : GetBaiduTrans.getEnToZhTrans(word));
		if ((transOpt ? chWordProcessor : enWordProcessor).contains(word))
		{
			localTransArea.setText((transOpt ? chWordProcessor : enWordProcessor).getExplanation(word));
		}
		else
			localTransArea.setText("未找到释义");
	}

	// 统一设置监听器
	private void setAction()
	{
		// 关于
		about.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "词典重构\n冯叶  2016.12.30 /南京大学", "关于",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// 翻译选项（英汉或汉英）
		transOption.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				// TODO Auto-generated method stub
				if (((String) transOption.getSelectedItem()).equals("英->汉"))
					transOpt = false;
				else
					transOpt = true;
			}
		});
		// 输入框
		inputField.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub
			}

			@Override
			// 联想
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					search(inputField.getText());
				}
				else
				{
					localTransArea.setText("");
					baiduTransArea.setText("");
					listModel.clear();
					if (inputField.getText().length() == 0)
						listModel.clear();
					else
					{
						WordProcessorBridge wordProcessor = (transOpt ? chWordProcessor : enWordProcessor);
						String[] words = wordProcessor.getAssociatedWords(inputField.getText(), 20);
						if (words.length == 0)
						{
							String possibleWord = wordProcessor.errorCorrect(inputField.getText());
							if (possibleWord != null)
							{
								localTransArea.setText("您是不是要找：" + possibleWord);
							}
						}
						else
							for (int i = 0; i < words.length; i++)
								listModel.addElement(words[i]);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
		// 恢复默认
		restoreDefault.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				restoreDefault();
			}
		});
		// 搜索按钮
		searchButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				search(inputField.getText());
			}
		});
		// 联想区域
		associatedWordsList.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				// TODO Auto-generated method stub
				if (associatedWordsList.getSelectedValue() != null)
					search(associatedWordsList.getSelectedValue());
			}
		});
		// 更改字体大小菜单项
		changeFontSize.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				Object[] sizeList = new Object[10];
				for (int i = 0; i < sizeList.length; i++)
					sizeList[i] = new Integer(8 + i);
				Integer fontSize = (Integer) JOptionPane.showInputDialog(null, "请选择字体大小：", "字体大小",
						JOptionPane.QUESTION_MESSAGE, null, sizeList, null);
				if (fontSize != null)
					changeFont(new Font(getFont().getFontName(), 0, fontSize));
			}
		});
		// 主题和字体设置菜单项
		theme1.addActionListener(new changeThemeActionListener(1));
		theme2.addActionListener(new changeThemeActionListener(2));
		theme3.addActionListener(new changeThemeActionListener(3));
		font1.addActionListener(new changeFontActionListener(new Font("黑体", 0, 13)));
		font2.addActionListener(new changeFontActionListener(new Font("宋体", 0, 13)));
		font3.addActionListener(new changeFontActionListener(new Font("楷体", 0, 13)));
	}

	// 更换主题监听器私有类
	private class changeThemeActionListener implements ActionListener
	{
		int changeOption = 1;

		public changeThemeActionListener(int opt)
		{
			// TODO Auto-generated constructor stub
			changeOption = opt;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			changeTheme(changeOption);
		}
	}

	// 更换字体监听器私有类
	private class changeFontActionListener implements ActionListener
	{
		Font font;

		public changeFontActionListener(Font font)
		{
			this.font = font;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			changeFont(font);
		}
	}

	// 恢复默认
	private void restoreDefault()
	{
		changeFont(new Font("黑体", 0, 13));
		changeTheme(0);
	}

	// 初始化
	public void initialize()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		getContentPane().setLayout(null);
		setBounds(400, 200, 520, 420);
		setJMenuBar(menuBar);
		setComponentsAttribution();
		setComponentsLocation();
		addAllComponents();
		setAction();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("英汉互译词典");
		setVisible(true);
		restoreDefault();
	}
}
