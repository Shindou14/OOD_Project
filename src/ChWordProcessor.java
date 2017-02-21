import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

//继承java项目一的单词处理类，由于汉英词典的某些方法不再使用，于是重写了两个方法
public class ChWordProcessor extends WordProcessor
{
	public ChWordProcessor(String dictPath) throws Exception
	{
		super();
		File file = new File(dictPath);
		if (!file.exists())
		{
			System.out.println("File not exists!");
			System.exit(1);
		}
		try (Scanner input = new Scanner(new FileInputStream(file), "gbk");)
		{
			while (input.hasNextLine())
			{
				String str = input.nextLine();
				String[] strs = str.split("\t");
				if (strs.length != 2)
					continue;
				Word word;
				word = new Word(strs[0], strs[1].substring(2));
				dict.put(strs[0], word);
				wordList.add(strs[0]);
			}
		}
	}

	@Override
	// 获得联想词
	public String[] getAssociatedWords(String str, int n)
	{
		if (str.length() == 0)
			return new String[0];
		String[] temp = new String[n];
		int count = 0;
		for (int i = 0; i < wordList.size(); i++)
		{
			if (count >= n)
				break;
			if (wordList.get(i).length() >= str.length() && wordList.get(i).substring(0, str.length()).equals(str))
				temp[count++] = wordList.get(i);
		}
		String[] outcome = new String[count];
		for (int i = 0; i < count; i++)
			outcome[i] = temp[i];
		return outcome;
	}
}
