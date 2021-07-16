import java.util.* ;
import edu.duke.* ;


class CharactersInPlay
{
        private ArrayList<String> characterList;
        private ArrayList<Integer> frequencyList;

        public CharactersInPlay() {
            characterList = new ArrayList<String>();
            frequencyList = new ArrayList<Integer>();
        }

        private void update(String person) {
            person = person.trim();
            int index = characterList.indexOf(person);

            if (index == -1) {
                characterList.add(person);
                frequencyList.add(1);
            } else {
                int currentFreq = frequencyList.get(index);
                frequencyList.set(index, currentFreq + 1);
            }
        }

        private void findAllCharacters() {
            FileResource fr = new FileResource();

            for (String line : fr.lines()) {
                int index = line.indexOf(".");

                if (index != -1) {
                    String characterName = line.substring(0, index);
                    update(characterName);
                }
            }
        }

        private void charactersWithNumParts(int num1, int num2) {
            for (int i = 0; i < characterList.size(); i++) {
                int currentFreq = frequencyList.get(i);

                if (currentFreq >= num1 && currentFreq <= num2) {
                    System.out.println(characterList.get(i));
                }
            }
        }

        private int findIndexOfMax() {
            int maxIndex = -1;
            int maxFreq = Integer.MIN_VALUE;

            for (int i = 0; i < frequencyList.size(); i++) {
                int currentFreq = frequencyList.get(i);

                if (currentFreq > maxFreq) {
                    maxFreq = currentFreq;
                    maxIndex = i;
                }
            }

            return maxIndex;
        }

        public void tester() {
            findAllCharacters();

            final int MIN_PART = 3;

            System.out.println("Main Characters:");

            for (int i = 0; i < characterList.size(); i++) {
                int currentFreq = frequencyList.get(i);

                if (currentFreq >= MIN_PART) {
                    System.out.println(characterList.get(i) + " " + currentFreq);
                }
            }

            System.out.println("Character with num of plays between 3 - 10:");
            charactersWithNumParts(3, 10);

            int maxIndex = findIndexOfMax();
            System.out.println("Character with the most speaking parts = " + characterList.get(maxIndex) + " " + frequencyList.get(maxIndex));
        }
}

class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique() {
        myWords.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();

        for(String word : fr.words()) {
            int index = myWords.indexOf(word.toLowerCase());

            if (index == -1) {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            } else {
                int currentFreq = myFreqs.get(index);
                myFreqs.set(index,  currentFreq + 1);
            }
        }
    }

    private int findIndexOfMax() {
        int maxIndex = -1;
        int maxFreq = Integer.MIN_VALUE;

        for (int i = 0; i < myFreqs.size(); i++) {
            int currentFreq = myFreqs.get(i);

            if (currentFreq > maxFreq) {
                maxFreq = currentFreq;
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public void tester() {
        findUnique();

        System.out.println("Number of unique words: " + myWords.size());

        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }

        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
