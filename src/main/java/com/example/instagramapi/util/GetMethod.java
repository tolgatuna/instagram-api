package com.example.instagramapi.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetMethod {


    public String performRequest(String uri, Map<String, String> params) throws IOException {

        String url = uri;
        if (params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue() + "&";
            }
            url = url.substring(0, url.length() - 1);
        }

//        try {
//            URL obj = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//
//            conn.setReadTimeout(5000);
//            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
//            conn.addRequestProperty("User-Agent", "Mozilla");
//            conn.addRequestProperty("Referer", "google.com");
//
//            System.out.println("Request URL ... " + url);
//
//            // normally, 3xx is redirect
//            int status = conn.getResponseCode();
//            while (status != HttpURLConnection.HTTP_OK) {
//                if (status == HttpURLConnection.HTTP_MOVED_TEMP
//                        || status == HttpURLConnection.HTTP_MOVED_PERM
//                        || status == HttpURLConnection.HTTP_SEE_OTHER)
//                System.out.println("Response Code ... " + status);
//                // get redirect url from "location" header field
//                String newUrl = conn.getHeaderField("Location");
//
//                // get the cookie if need, for login
//                String cookies = conn.getHeaderField("Set-Cookie");
//
//                // open the new connnection again
//                conn = (HttpURLConnection) new URL(newUrl).openConnection();
//                conn.setRequestProperty("Cookie", cookies);
//                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
//                conn.addRequestProperty("User-Agent", "Mozilla");
//                conn.addRequestProperty("Referer", "google.com");
//
//                System.out.println("Redirect to URL : " + newUrl);
//                status = conn.getResponseCode();
//            }
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuffer html = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                html.append(inputLine);
//            }
//            in.close();
//
//            System.out.println("URL Content... \n" + html.toString());
//            System.out.println("Done");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("URL : " + url);

        URL conUrl = new URL(url);
        if(url.startsWith("https://")) {
            HttpsURLConnection con = (HttpsURLConnection)conUrl.openConnection();
            con.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
            int response = con.getResponseCode();
            if(response == HttpURLConnection.HTTP_MOVED_PERM || response == HttpURLConnection.HTTP_MOVED_TEMP) {
                String location = con.getHeaderField("Location");
                performRequest(location, new HashMap<>());
            }
        } else {
            HttpURLConnection con = (HttpsURLConnection)conUrl.openConnection();
            con.setInstanceFollowRedirects(false);  //you still need to handle redirect manully.
            int response = con.getResponseCode();
            if(response == HttpURLConnection.HTTP_MOVED_PERM || response == HttpURLConnection.HTTP_MOVED_TEMP) {
                String location = con.getHeaderField("Location");
                performRequest(location, new HashMap<>());
            }
        }

//        HttpClient instance = HttpClientBuilder.create()
//                .disableRedirectHandling()
//                .build();
//
//        HttpGet httpGet = new HttpGet(url);
//
//        HttpResponse response = instance.execute(httpGet);
//        if(response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_MOVED_PERM || response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
//            System.out.println(uri);
//            Header[] locations = response.getHeaders("Location");
//            if (locations.length > 0 ) {
//                String location = locations[0].getValue();
//                performRequest(location, new HashMap<>());
//            }
//        }

        return "";
    }

}
