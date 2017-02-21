//桥接模式应用
public class WordProcessorBridge
{
	private WordProcessor realWordProcessor;

	public WordProcessorBridge(String option) throws Exception
	{
		WordProcessorFactory wpf = new WordProcessorFactory();
		if (option == "ch")
			realWordProcessor = wpf.produceChWordProcessor();
		else if (option == "en")
			realWordProcessor = wpf.produceEnWordProcessor();
		else
			System.exit(-1);
	}

	public boolean contains(String s)
	{
		return realWordProcessor.contains(s);
	}

	public String[] getAssociatedWords(String str, int n)
	{
		return realWordProcessor.getAssociatedWords(str, n);
	}

	public String errorCorrect(String str)
	{
		return realWordProcessor.errorCorrect(str);
	}

	public String getExplanation(String word)
	{
		if (!contains(word))
			return null;
		return realWordProcessor.getDict().get(word).getExplanation();
	}
}
