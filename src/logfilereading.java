import edu.duke.*  ;
import java.util.* ;
import java.util.* ;
class logfilereading
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private  int bytesReturned;
    public logfilereading(String ip , Date accessdateTime ,String requestor , int statusCodes , int bytesReturner)
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

