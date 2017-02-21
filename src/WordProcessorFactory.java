//单词处理的工厂方法，工厂方法模式的应用
public class WordProcessorFactory
{
	public WordProcessor produceEnWordProcessor() throws Exception
	{
		return new WordProcessor("dictionary\\enToChDict.txt");
	}

	public WordProcessor produceChWordProcessor() throws Exception
	{
		return new ChWordProcessor("dictionary\\chToEnDict.txt");
	}
}
