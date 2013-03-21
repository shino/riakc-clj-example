import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakLink;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.query.WalkResult;
import java.util.Collection;
import java.util.Iterator;

public class LinkWalkAppHttp
{
    public static void main( String[] args ) throws RiakException
    {
        IRiakClient client = RiakFactory.httpClient();
        Bucket b = client.fetchBucket("people").execute();

        IRiakObject myObject = b.fetch("sean").execute();
        WalkResult result = client.walk(myObject)
                            .addStep("people", "friend", true)
                            .addStep("people", "friend")
                            .execute();

        Iterator<Collection<IRiakObject>> i = result.iterator();
        int count = 0;
        while (i.hasNext())
        {
             count++;
             Collection<IRiakObject> c = i.next();
             for (IRiakObject o : c)
             {
                 System.out.println(count + ": " + o.getValueAsString());
             }
        }

        client.shutdown();
    }
}
