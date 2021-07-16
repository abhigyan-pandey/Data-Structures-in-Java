import edu.duke.* ;
import java.util.*;

public class websitevisits
{
    public HashMap<String, Integer> countOfWebsiteVisits()
    {
        FileResource fr = new FileResource() ;
        HashMap<String, Integer> map = new HashMap<String , Integer>() ;
        for(String line : fr.lines())
        {
            WebLogParser parser = new WebLogParser() ;
            logfilereading record = parser.parseEntry(line) ;
            String ipaddress = record.getIpAddress() ;
            if(map.containsKey(ipaddress))
            {
                map.put(ipaddress , map.get(ipaddress)+1);
            }
            else
                map.put(ipaddress , 1) ;
        }
        return map ;
    }

    public static void main(String[] args)
    {
        websitevisits obj = new websitevisits() ;
        HashMap<String ,Integer> map = obj.countOfWebsiteVisits() ;
        System.out.println(map.size());
        for(String key : map.keySet())
        {
            System.out.println("The key is " + key + " while it's value is" + map.get(key));
        }
    }
}
