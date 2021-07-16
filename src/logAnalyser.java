import edu.duke.*  ;
import java.util.* ;
import java.util.* ;

class logAnalyser
{
    private ArrayList<logfilereading> records ;

    public logAnalyser()
    {
        records = new ArrayList<>() ;
    }
    public void readFile(String filename)
    {
        FileResource fr = new FileResource(filename) ;
        for(String line : fr.lines())
        {
             WebLogParser parser = new WebLogParser();
             logfilereading parsedValue = parser.parseEntry(line);
             records.add(parsedValue) ;
        }
    }
    public void printAll()
    {
        for(logfilereading le : records)
        {
            System.out.println(le.toString());
        }
    }
    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (logfilereading le : records) {
            if (!uniqueIps.contains(le.getIpAddress())) {
                uniqueIps.add(le.getIpAddress());
            }
        }

        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (logfilereading le : records) {
            if (le.getStatusCode() > num)
            {
                System.out.println(le);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String day)
    {
        ArrayList<String> uniqueIps = new ArrayList<>() ;
        for(logfilereading le  : records)
        {
            String dateTime = le.getAccessTime().toString() ;
            if(dateTime.contains(day))
            {
                if(!uniqueIps.contains(le.getIpAddress()))
                    uniqueIps.add(le.getIpAddress()) ;
            }
        }
        return  uniqueIps ;
    }
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (logfilereading le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }
        return uniqueIps.size();
    }
}