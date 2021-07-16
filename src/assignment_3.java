import java.util.* ;
import edu.duke.* ;
public class assignment_3
{
    void countWordLengths(FileResource fr , int [] counts)
    {
        for(String word : fr.words())
        {
            int nonLetterCount = 0;

            if (!Character.isLetter(word.charAt(0))) {
                nonLetterCount++;
            }

            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                nonLetterCount++;
            }

            int wordLength = word.length() - nonLetterCount;
            int maxLength = counts.length - 1;

            if (wordLength >= maxLength) {
                counts[maxLength]++;
            } else {
                counts[wordLength]++;
            }
        }
    }
    public int indexOfMax(int values[]) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
