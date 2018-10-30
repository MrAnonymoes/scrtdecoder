package scrtdecoder.com.secret;

public class Encode {
    private char[] codeWord;
    private char[] message;
    private char[] encodedResult;

    Encode()
    {
        this.message = "".toCharArray();
        this.codeWord = Homescreen.CODE_WORD.toLowerCase().toCharArray();

    }

    public void putText(String message)
    {
        this.message = message.toLowerCase().toCharArray();
        this.encodedResult = new char[this.message.length];
    }

    public String encode()
    {
        for (int i = 0; i < message.length; i++)
        {
            if((int)message[i] < 97 || (int)message[i] > 122)
            {
                encodedResult[i] = message[i];
            }
            else
            {
                int letterValue = (int)message[i];
                int codeValue = letterValue - (codeWord[i % codeWord.length]);
                if (codeValue < 0)
                    codeValue += 26;
                encodedResult[i] = (char)(codeValue + 'a');
            }
        }

        return new String(encodedResult);
    }





}
