import java.util.* ;
import edu.duke.* ;
class codons
{
    //  Assignment-1 (CODONS)
    private HashMap<String, Integer> codonCounts;

    public codons() {
        codonCounts = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna) {
        codonCounts.clear();

        for (int i = start; i < (dna.length() - 3); i += 3) {
            String codon = dna.substring(i, i + 3);

            if (codonCounts.containsKey(codon)) {
                codonCounts.put(codon, codonCounts.get(codon) + 1);
            } else {
                codonCounts.put(codon, 1);
            }
        }
    }

    private String getMostCommonCodon() {
        int maxCount = Integer.MIN_VALUE;
        String mostCommonCodon = "";

        for (String key : codonCounts.keySet()) {
            int currCount = codonCounts.get(key);

            if (currCount > maxCount) {
                maxCount = currCount;
                mostCommonCodon = key;
            }
        }

        return mostCommonCodon;
    }

    private void printCodonCounts(int start, int end) {
        for (String key : codonCounts.keySet()) {
            int currCount = codonCounts.get(key);

            if (currCount >= start && currCount <= end) {
                System.out.println(key + " -> " + currCount);
            }
        }
    }

}

class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedWord;

    private Random myRandom;

    private int totalReplacedWords;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        usedWord = new ArrayList<String>();
        myRandom = new Random();
        totalReplacedWords = 0;
    }

    public GladLib(String source){
        initializeFromSource(source);
        usedWord = new ArrayList<String>();
        myRandom = new Random();
        totalReplacedWords = 0;
    }

    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWord.contains(sub) && sub != "**UNKNOWN**") {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWord.add(sub);
        totalReplacedWords++;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        usedWord.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal replaced words = " + totalReplacedWords);
    }
}

class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWord;
    private ArrayList<String> usedCategory;

    private Random myRandom;

    private int totalReplacedWords;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedWord = new ArrayList<String>();
        usedCategory = new ArrayList<String>();
        myRandom = new Random();
        totalReplacedWords = 0;
    }

    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        usedWord = new ArrayList<String>();
        usedCategory = new ArrayList<String>();
        myRandom = new Random();
        totalReplacedWords = 0;
    }

    private void initializeFromSource(String source) {
        String[] categoryList = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};

        for(String category : categoryList) {
            ArrayList<String> list = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (!usedCategory.contains(label)) {
            usedCategory.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWord.contains(sub) && sub != "**UNKNOWN**") {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWord.add(sub);
        totalReplacedWords++;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap() {
        int totalWords = 0;

        for (String key : myMap.keySet()) {
            totalWords += myMap.get(key).size();
        }

        return totalWords;
    }

    private int totalWordsConsidered() {
        int totalWords = 0;

        for (String key : myMap.keySet()) {
            if (usedCategory.contains(key)) {
                totalWords += myMap.get(key).size();
            }
        }

        return totalWords;
    }

    public void makeStory(){
        usedWord.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal replaced words = " + totalReplacedWords);
        System.out.println("Total words in map = " + totalWordsInMap());
        System.out.println("Total words considered = " + totalWordsConsidered());
    }
}

