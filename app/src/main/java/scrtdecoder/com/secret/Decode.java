package scrtdecoder.com.secret;

import android.util.Log;

public class Decode {
    private char[] codeWord;
    private char[] message;
    private char[] decodedResult;
    Decode()
    {
        this.codeWord = Homescreen.CODE_WORD.toLowerCase().toCharArray();
        this.message = "".toCharArray();
    }

    public void putText(String message)
    {
        this.message = message.toLowerCase().toCharArray();
        this.decodedResult = new char[this.message.length];
    }

    public String decode()
    {
        for (int i = 0; i < message.length; i++)
        {
            if ((int)message[i] > 122 || (int)message[i] < 97)
            {
                decodedResult[i] = message[i];
                Log.d("MyLog", "for loop go to if with char number: " + (int)message[i] + " which leads to char: " + message[i]);
            }
            else
            {
                Log.d("MyLog", "for loop go to else with char number: " + (int)message[i] + " which leads to char: " + message[i]);
                decodedResult[i] = (char)((int)codeWord[i % codeWord.length] + ((int)message[i] - 'a'));
                Log.d("MyLog", "for loop go to else :: decodedResult: message char " + (int)message[i] + " becomes char number:" + (int)decodedResult[i] + "which is char: " + decodedResult[i]);
                if((int)decodedResult[i] > 122)
                    decodedResult[i] =  (char)((int)decodedResult[i] - 26);
            }
        }

        return new String(this.decodedResult);
    }
}
