import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakLink;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.query.WalkResult;
import java.util.Collection;
import java.util.Iterator;

public class LinkWalkAppPB
{
    public static void main( String[] args ) throws RiakException
    {
        IRiakClient client = RiakFactory.pbcClient();
		
        Bucket b = client.fetchBucket("test_bucket").execute();
		
        // Create some records in riak
        b.store("user_1234", "{e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;namee86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;:e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;Brian Roache86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;}").execute();
        b.store("user_2345","{e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;namee86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;:e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;Russell Browne86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;}").execute();
        b.store("user_3456","{e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;namee86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;:e86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;Sean Cribbse86cfcd75c1dbaa5bacb8e6d6415951a19054778quot;}").execute();

        // Retrieve an object, add links, store back to Riak
        IRiakObject myObject = b.fetch("user_1234").execute(); 
        myObject.addLink(new RiakLink("test_bucket","user_2345","friend"))
                .addLink(new RiakLink("test_bucket","user_3456","friend"));
        b.store(myObject).execute();
      
        // Walk the links
        myObject = b.fetch("user_1234").execute(); 
        WalkResult result = client.walk(myObject)
                            .addStep("test_bucket", "friend", true)
                            .addStep("test_bucket", "friend")
                            .execute();

        Iterator<Collection<IRiakObject>> i = result.iterator();
        int count = 0;
        while (i.hasNext())
        {
             count++;
             Collection<IRiakObject> c = i.next();
             for (IRiakObject o : c)
             {
                 System.out.println(count + " " + o.getValueAsString());
             }
        }
        
        client.shutdown();
    }
}
