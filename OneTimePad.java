package onetimepad;

import java.util.Random;
import java.util.Scanner;

/**
 *Very simple example of a one time pad
 * works only with letters.  No digits may be used as they would just be decrypted wrong.
 * Not a perfect program.  Spaces are not decrypted correctly.
 * Again, this is just a very simple example of a one time pad that made to better understand how it would work.
 * Gives a rough idea on how it would function.  Do not take as a perfect example.
 * @author aguzm
 */
public class OneTimePad 
{
    public static void main(String[] args) 
    {
        int option=0; 
        boolean done = true; 
        String message; 
        String key; 
        while(done)
        {
            System.out.print("Enter 1 if you wish to encrypte a message.  Enter 2 if you wish to decrypt a message:"); 
            Scanner scan = new Scanner(System.in).useDelimiter("\n"); 
            option = scan.nextInt();
            if(option == 1)
            {
                System.out.print("Please enter the message you wish to encrypte:");
                message = scan.next();
                done = false;
                System.out.println(encrypte(message)); 
            }
            else if(option == 2)
            {
                System.out.print("Please enter the message you wish to decrypte:");
                message = scan.next();
                System.out.print("Please enter the key used(spaces between each key please.  End with '.'): ");
                key = scan.next();
                done = false;
                System.out.println(decrypte(message, key));
            }
        }
    }
    
    static String decrypte(String message, String key)
    {
        String decMessage="";
        char currLet;
        int counter = 0;
        String currNumber = "";
        int keyStringIndex = 0;
        String[] keyString = new String[message.length()];
        int[] keys = new int[message.length()];
        
        do
        {
            currNumber += key.charAt(counter);
            counter++;
            if(key.charAt(counter)==' ')
            {
                counter++;
                keyString[keyStringIndex] = currNumber;
                currNumber = "";
                keyStringIndex++;
            }
            if(key.charAt(counter) == '.')
            {
                keyString[keyStringIndex] = currNumber;
            }
        }while(key.charAt(counter) != '.');
        
        for(int i = 0; i < keyString.length; i++)
        {
            keys[i] = Integer.parseInt(keyString[i]);
        }
        
        for(int d = 0; d < message.length(); d++)
        {
            currLet = (char)((int)((message.charAt(d) - keys[d])));
            if((int)(currLet) < 97)
            {
                currLet = (char)((int)(currLet)+26);
            }
            decMessage += currLet;
        }
        return decMessage;
    }
    
    static String encrypte(String message)
    {
        int[] key = new int[message.length()];
        char nextLet;
        String encMessage = "";
        Random gen = new Random();
        for(int i = 0; i < message.length(); i++)
        {
            if(message.charAt(i) == ' ')
            {
                key[i] = 0;
            }
            else
            {
                key[i] = gen.nextInt(26);
            }
        }
        
        for(int k = 0; k < message.length(); k++)
        {
            nextLet = (char)((int)((key[k] + message.charAt(k))%122));
            if((int)(nextLet) < 97)
            {
                nextLet = (char)((int)(nextLet)+96);
            }
            encMessage += nextLet;
            System.out.print(key[k] + " ");
        }
        
        System.out.println();
        return encMessage;
    }
}
