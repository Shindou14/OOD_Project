
//原有单词类
public class Word
{
	private int ID = 0;
	private String name;
	private String phoneticSymbol = "-";
	private String explanation;

	public Word(int ID, String name, String phoneticSymbol, String explanation)
	{
		this.ID = ID;
		this.name = name;
		this.phoneticSymbol = phoneticSymbol;
		this.explanation = explanation;
	}

	public Word(String name, String explanation)
	{
		this.name = name;
		this.explanation = explanation;
	}

	public int getID()
	{
		return ID;
	}

	public String getName()
	{
		return name;
	}

	public String getPhoneticSymbol()
	{
		return phoneticSymbol;
	}

	public String getExplanation()
	{
		return explanation;
	}

	public boolean equals(Word o)
	{
		return name.equals(o.getName());
	}

	public void print()
	{
		System.out.println(ID);
		System.out.println(name);
		System.out.println(phoneticSymbol);
		System.out.println(explanation);
		System.out.println();
	}
}
