import edu.duke.*;

class CaesarCrackerweek4 {
    char mostCommon;

    public CaesarCrackerweek4() {
        mostCommon = 'e';
    }

    public CaesarCrackerweek4(char c) {
        mostCommon = c;
    }

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipherweek4 cc = new CaesarCipherweek4(key);
        return cc.decrypt(encrypted);

    }

}
