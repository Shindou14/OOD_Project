import java.util.HashMap;

public abstract class AbstractErrorCorrect implements ErrorCorrect
{
	protected HashMap<String, Word> dict;

	public AbstractErrorCorrect(HashMap<String, Word> dict)
	{
		this.dict = dict;
	}
}
