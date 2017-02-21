import java.util.HashMap;

public class DoubleClickErrorCorrect extends AbstractErrorCorrect
{

	public DoubleClickErrorCorrect(HashMap<String, Word> dict)
	{
		super(dict);
	}

	public String correct(String str)
	{
		for (int i = str.length() - 2; i >= 0; i--)
			if (str.charAt(i) == str.charAt(i + 1) && dict.containsKey(str.substring(0, i) + str.substring(i + 1)))
				return str.substring(0, i) + str.substring(i + 1);
		return null;
	}
}
