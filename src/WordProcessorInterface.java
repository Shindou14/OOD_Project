import java.util.HashMap;

public interface WordProcessorInterface
{
	public boolean contains(String s);

	public String[] getAssociatedWords(String str, int n);

	public String errorCorrect(String str);

	public HashMap<String, Word> getDict();
}
