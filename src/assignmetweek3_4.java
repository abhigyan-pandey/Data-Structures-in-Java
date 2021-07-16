import edu.duke.* ;
import java.text.*;
import java.util.*;
import java.util.Calendar;

class logfilereadingweek3
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private  int bytesReturned;
    public logfilereadingweek3(String ip , Date accessdateTime ,String requestor , int statusCodes , int bytesReturner)
    {
        ipAddress = ip;
        accessTime = accessdateTime ;
        request = requestor ;
        statusCode = statusCodes ;
        bytesReturned = bytesReturner ;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    public static void main(String[] args) {
        Date value = new Date() ;
        logfilereading log = new logfilereading("192.168.1.5" ,value ,"GET ", 200 ,2048) ;
    }
}


class WebLogParserweek3
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z", Locale.US);
    private static String munchTo(StringBuilder sb, String delim) {
        int x = sb.indexOf(delim);
        if (x == -1) {
            x = sb.length();
        }
        String ans = sb.substring(0,x);
        sb.delete(0, x + delim.length());
        return ans;
    }
    public logfilereading parseEntry(String line)
    {
        //Assumes line is vald and in this format:
        //110.76.104.12 - - [30/Sep/2015:07:47:11 -0400] "GET //favicon.ico HTTP/1.1" 200 3426
        StringBuilder sb = new StringBuilder(line);
        String ip = munchTo(sb, " ");
        munchTo(sb, " "); //ignore -
        munchTo(sb, " ["); //ignore -, and eat the leading [
        String dateStr = munchTo(sb, "] \""); //]-space is intentional: eat both
        Date date = parseDate(dateStr);
        String request = munchTo(sb, "\" "); // quote-space is intentional: eat both
        String statusStr = munchTo(sb, " ");
        int status = Integer.parseInt(statusStr);
        String byteStr = munchTo(sb, " ");
        int bytes = Integer.parseInt(byteStr);
        return new logfilereading(ip, date, request, status, bytes);
    }
    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return  dateFormat.parse(dateStr, pp);
    }
}

public class LogAnalyzerweek3
{
    private ArrayList<logfilereadingweek3> records;

    public LogAnalyzerweek3() {
        records = new ArrayList<logfilereadingweek3>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            WebLogParserweek3 parser = new WebLogParserweek3();
            logfilereadingweek3 newEntry = parser.parseEntry(line);
            records.add(newEntry);
        }
    }

    public void printAll() {
        for (logfilereadingweek3 le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (logfilereadingweek3 le : records) {
            if (!uniqueIps.contains(le.getIpAddress())) {
                uniqueIps.add(le.getIpAddress());
            }
        }

        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (logfilereadingweek3 le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (logfilereadingweek3 le : records) {
            if (le.getAccessTime().toString().contains(someday)) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }

        return uniqueIps;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIps = new ArrayList<String>();

        for (logfilereadingweek3 le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
        }

        return uniqueIps.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();

        for (logfilereadingweek3 le : records) {
            String ip = le.getIpAddress();

            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }

        return ipCounts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
        int maxVisit = Integer.MIN_VALUE;

        for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);

            if (currentCount > maxVisit) {
                maxVisit = currentCount;
            }
        }

        return maxVisit;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
        ArrayList<String> ipList = new ArrayList<String>();
        int maxVisit = mostNumberVisitsByIP(ipCounts);

        for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);

            if (currentCount == maxVisit) {
                ipList.add(key);
            }
        }

        return ipList;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();

        for (logfilereadingweek3 le : records) {
            String accessTime = le.getAccessTime().toString();
            String day = accessTime.substring(4, 10);
            String currIp = le.getIpAddress();
            ArrayList<String> ipList;

            if (!ipsPerDay.containsKey(day)) {
                ipList = new ArrayList<String>();
            } else {
                ipList = ipsPerDay.get(day);
            }

            ipList.add(currIp);
            ipsPerDay.put(day, ipList);
        }

        return ipsPerDay;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsPerDay) {
        String day = "";
        int maxVisit = Integer.MIN_VALUE;

        for (String key : ipsPerDay.keySet()) {
            int currentVisit = ipsPerDay.get(key).size();

            if (currentVisit > maxVisit) {
                maxVisit = currentVisit;
                day = key;
            }
        }

        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
        ArrayList<String> ipList = ipsPerDay.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();

        for (String ip : ipList) {
            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }

        return iPsMostVisits(ipCounts);
    }
}



