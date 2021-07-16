import java.util.* ;
import edu.duke.* ;
class assignment_2
{

    String encryption(String input , int key)
    {
        StringBuilder sb = new StringBuilder(input) ;
        if(input == null || key < 0)
            return null ;
        else if(key == 0)
            return input;
        else
        {
            for(int i =0 ;i<input.length();i++)
            {
                char current_character = input.charAt(i) ;
                key %= 26 ;
                if( Character.isLowerCase(current_character))
                {
                    int integer_Value = (int)current_character ;
                    integer_Value = ((integer_Value + key - 97 )% 26 ) + 97 ;
                    char replacable =(char) integer_Value ;
                    sb.setCharAt(i , replacable);
                }
                else if(Character.isUpperCase(current_character))
                {
                    int integer_Value = (int) current_character ;
                    integer_Value = ((integer_Value + key - 65 )% 26 ) + 65 ;
                    char replacable =(char) integer_Value ;
                    sb.setCharAt(i , replacable);
                }
            }
            return sb.toString() ;
        }
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String firstShiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String secondShiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));

            if (index != -1) {
                String shiftedAlphabet;

                if (i % 2 == 0) {
                    shiftedAlphabet = firstShiftedAlphabet;
                } else {
                    shiftedAlphabet = secondShiftedAlphabet;
                }

                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }

        return encryptedMessage.toString();
    }


    public static void main(String[] args)
    {
        assignment_2 obj = new assignment_2() ;
        System.out.println(obj.encryption("Qc reqi mw Eflmkcer Terhic erh xlmw mw e Wigvix Gshi" , 22) );
    }
}