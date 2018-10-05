package com.example.instagramapi.util;

import com.example.instagramapi.entities.InstagramConfig;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostMethod extends APIMethod {
    Map<String, Object> postParameters;
    private RequestConfig proxyConfig;

    public PostMethod() {
        super();
        this.type = "POST";
    }

    public PostMethod(String methodUri) {
        this.type = "POST";
    }

    @Override
    protected InputStream performRequest() {
        HttpResponse response;
        HttpPost post = new HttpPost(this.methodUri);

        post.addHeader("Content-type", "application/x-www-form-urlencoded");
        post.addHeader("Accept", "*/*");

        if (InstagramConfig.hasProxy) {

            if (proxyConfig == null) {
                HttpHost proxy = new HttpHost(InstagramConfig.proxyAddress, InstagramConfig.proxyPort, "http");
                proxyConfig = RequestConfig.custom().setProxy(proxy).build();
            }

            post.setConfig(proxyConfig);
        }


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        for (Map.Entry<String, Object> arg : getPostParameters().entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(arg.getKey(), arg.getValue().toString()));
            System.out.println(arg.getKey() + " " + arg.getValue().toString());
        }
        InputStream stream = null;
        try {

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = client.execute(post);
            stream = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }

    public Map<String, Object> getPostParameters() {
        return postParameters;
    }

    public PostMethod setPostParameters(Map<String, Object> postParameters) {
        this.postParameters = postParameters;
        return this;
    }

}
