import java.util.* ;
import edu.duke.* ;
public class assignment_1
{
   boolean isVowel(char ch)
   {
    if(Character.isLowerCase(ch)) {
       if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
          return true;
    }
    else
    {
       if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
          return true;
    }
    return  false;
   }

   public String replaceVowels(String phrase , char ch)
   {
      StringBuilder sb = new StringBuilder(phrase) ;
      for(int i =0 ; i<phrase.length() ;i++)
      {
         char current_character = phrase.charAt(i) ;
         if(isVowel(current_character))
         sb.setCharAt(i ,ch);
      }
      return sb.toString();
   }
   void test_replace_Vowels()
   {
      String a = "Abhigyan Pandey" ;
      System.out.println(replaceVowels(a , '$')) ;
   }

   public String emphasize(String phrase , char ch)
   {
      StringBuilder sb= new StringBuilder(phrase) ;
      for(int i = 0 ; i<phrase.length() ; i++)
      {
         if(phrase.charAt(i) == ch)
         {
            if((i+1) % 2 == 0)
            sb.setCharAt(i ,'+');
            else
            sb.setCharAt( i ,'*');
         }
      }
      return sb.toString() ;
   }

   public static void main(String[] args)
   {
      assignment_1 obj = new assignment_1() ;
      obj.test_replace_Vowels();
      System.out.println(obj.emphasize("dna ctgaaactga" , 'a'));

   }
}
