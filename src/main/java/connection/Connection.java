package connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class Connection {

    public String connect(String url, String token) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("apikey", "97ff2a53cb3e46c4a47f703a888bf8c0");
        HttpResponse httpresponse = client.execute(httpGet);
        return EntityUtils.toString(httpresponse.getEntity());
    }

}
