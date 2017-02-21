import java.util.HashMap;

public class MissInputErrorCorrect extends AbstractErrorCorrect
{
	public MissInputErrorCorrect(HashMap<String, Word> dict)
	{
		super(dict);
	}

	public String correct(String str)
	{
		char[] charList = "abcdefghijklmnopqrstuvwxyz- ".toCharArray();
		for (int i = str.length() - 1; i >= 0; i--)
			for (char ch : charList)
				if (dict.containsKey(str.substring(0, i) + ch + str.substring(i)))
					return str.substring(0, i) + ch + str.substring(i);

		return null;
	}
}
