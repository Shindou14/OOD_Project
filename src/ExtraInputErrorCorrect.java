import java.util.HashMap;

public class ExtraInputErrorCorrect extends AbstractErrorCorrect
{
	public ExtraInputErrorCorrect(HashMap<String, Word> dict)
	{
		super(dict);
	}

	public String correct(String str)
	{
		for (int i = str.length() - 1; i >= 0; i--)
			if (dict.containsKey(str.substring(0, i) + str.substring(i + 1)))
				return str.substring(0, i) + str.substring(i + 1);
		return null;
	}
}
