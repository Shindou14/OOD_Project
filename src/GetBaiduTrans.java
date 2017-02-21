import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baidu.translate.demo.TransApi;

//获取百度翻译
public class GetBaiduTrans
{

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer

	private static final String APP_ID = "20161229000034867";
	private static final String SECURITY_KEY = "xoBBtihLG9Cov3FQblhC";

	// 私有方法
	private static String getTrans(String searchWord, String from, String to)
	{
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		// 根据JSON格式的数据取得其中的翻译结果
		String[] outcome = api.getTransResult(searchWord, from, to).split("\"");
		// 匹配UTF-8编码的正则表达式（斜杠u加四个十六进制数）不区分大小写
		Pattern pattern = Pattern.compile("(?i)\\\\u([0-9a-f]{4})");
		Matcher matcher = pattern.matcher(outcome[outcome.length - 2]);
		StringBuffer stringBuffer = new StringBuffer();
		while (matcher.find())
		{
			// 以十六进制将编码字符串解析为char编码
			matcher.appendReplacement(stringBuffer, Character.toString((char) Integer.parseInt(matcher.group(1), 16)));
		}
		matcher.appendTail(stringBuffer);
		return stringBuffer.toString();
	}

	// 获得百度英汉翻译
	public static String getEnToZhTrans(String searchWord)
	{
		return getTrans(searchWord.trim(), "en", "zh");

	}

	// 获得百度汉英翻译
	public static String getZhToEnTrans(String searchWord)
	{
		return getTrans(searchWord.trim(), "zh", "en");
	}

	public static void main(String[] args)
	{
		// 测试代码
		System.out.println(getEnToZhTrans("xubobaobo"));
		System.out.println(getZhToEnTrans("许博"));
	}
}
