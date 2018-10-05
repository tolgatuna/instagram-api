package com.example.instagramapi;

import com.example.instagramapi.entities.InstagramConfig;
import com.example.instagramapi.util.UriFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstagramApiApplicationTests {

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void givenRedirectsAreDisabled_whenConsumingUrlWhichRedirects_thenNotRedirected()
//            throws IOException {
//        DefaultHttpClient instance = new DefaultHttpClient();
//
//        HttpParams params = new BasicHttpParams();
//        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
//        // HttpClientParams.setRedirecting(params, false); // alternative
//
////        HttpGet httpGet = new HttpGet("http://t.co/I5YYd9tddw");
//        HttpGet httpGet = new HttpGet("https://api.instagram.com/oauth/authorize/?response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth%2Finstagram%2F&client_id=045120c67829477fa9b607ba9483625d");
//        httpGet.setParams(params);
//        CloseableHttpResponse response = instance.execute(httpGet);
//
//        Assert.assertThat(response.getStatusLine().getStatusCode(), equalTo(302));
//    }

    @Test
    public void givenRedirectsAreDisabled_whenConsumingUrlWhichRedirects_thenNotRedirected()
            throws IOException, URISyntaxException {

        Map<String, String> args = new HashMap<>();
        args.put("client_id", InstagramConfig.clientId);
        args.put("redirect_uri", InstagramConfig.clientUri);
        args.put("response_type", "code");

        URIBuilder builder = new URIBuilder(UriFactory.Auth.USER_AUTHORIZATION);
        for (Map.Entry<String, String> entry : args.entrySet()) {
            builder.addParameter(entry.getKey(), entry.getValue());
        }

        //URI url = builder.build();
        HttpClientContext context = HttpClientContext.create();

        HttpClient instance = HttpClientBuilder.create().disableRedirectHandling().build();

        // "https://api.instagram.com/oauth/authorize/?response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth%2Finstagram%2F&client_id=045120c67829477fa9b607ba9483625d"
        HttpResponse response = instance.execute(new HttpGet("http://t.co/I5YYd9tddw"), context);

        List<URI> redirectLocations = context.getRedirectLocations();

        for (URI uri : redirectLocations) {
            System.out.println(uri);
        }

        Assert.assertThat(response.getStatusLine().getStatusCode(), equalTo(302));
    }

}
