
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordProcessor implements WordProcessorInterface
{
	protected HashMap<String, Word> dict = new HashMap<>();
	protected ArrayList<String> wordList = new ArrayList<>();
	protected ErrorCorrect[] errorCorrects = new ErrorCorrect[4];

	protected WordProcessor()
	{
		errorCorrects[0] = new DoubleClickErrorCorrect(dict);
		errorCorrects[1] = new MissInputErrorCorrect(dict);
		errorCorrects[2] = new ExtraInputErrorCorrect(dict);
		errorCorrects[3] = new WrongInputErrorCorrect(dict);
	}

	public WordProcessor(String dictPath) throws Exception
	{
		this();
		File file = new File(dictPath);
		if (!file.exists())
		{
			System.out.println("File not exists!");
			System.exit(1);
		}
		try (Scanner input = new Scanner(new FileInputStream(file), "gbk");)
		{
			input.nextLine();// First line is not valid;
			while (input.hasNextLine())
			{
				String str = input.nextLine();
				String[] strs = str.split("\t");
				Word word;
				if (strs.length == 4)
					word = new Word(Integer.parseInt(strs[0]), strs[1], strs[2], strs[3]);
				else
					word = new Word(Integer.parseInt(strs[0]), strs[1], "", strs[2]);
				dict.put(strs[1], word);
				wordList.add(strs[1]);
			}
		}
	}

	public HashMap<String, Word> getDict()
	{
		return dict;
	}

	public boolean contains(String s)
	{
		return dict.containsKey(s);
	}

	// 高效率的二分查找
	private int binSearch(String str)
	{
		if (str.compareTo(wordList.get(0)) <= 0)
			return 0;
		if (str.compareTo(wordList.get(wordList.size() - 1)) == 0)
			return wordList.size() - 1;
		if (str.compareTo(wordList.get(wordList.size() - 1)) > 0)
			return wordList.size();

		int low = 0, high = wordList.size() - 1;
		while (low < high - 1)
		{
			if (str.compareTo(wordList.get((low + high) / 2)) == 0)
				return (low + high) / 2;
			else if (str.compareTo(wordList.get((low + high) / 2)) > 0)
			{
				low = (low + high) / 2;
				continue;
			}
			else
			{
				high = (low + high) / 2;
			}
		}
		return high;
	}

	// 获得联想词
	public String[] getAssociatedWords(String str, int n)
	{
		if (str.length() == 0)
			return new String[0];
		if (binSearch(str) == wordList.size())
			return new String[0];
		ArrayList<String> strs = new ArrayList<>();
		int i = 0;
		int nextWordIndex = binSearch(str);
		for (; i + nextWordIndex < wordList.size() && i < n; i++)
		{
			if (wordList.get(i + nextWordIndex).contains(str))
				strs.add(wordList.get(i + nextWordIndex));
			else
				break;
		}

		return strs.toArray(new String[strs.size()]);
	}

	// 纠错
	public String errorCorrect(String str)
	{
		String correctWord = null;
		for (int i = 0; i < errorCorrects.length; i++)
			if ((correctWord = errorCorrects[i].correct(str)) != null)
				return correctWord;
		return correctWord;
	}

}
