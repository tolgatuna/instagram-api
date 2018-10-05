package com.example.instagramapi.services;

import com.example.instagramapi.entities.InstagramConfig;
import com.example.instagramapi.entities.InstagramRecentMedia;
import com.example.instagramapi.entities.InstagramRecentMediaData;
import com.example.instagramapi.entities.InstagramRecentMediaImage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.instagramapi.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PageContentInstagramService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<InstagramRecentMediaImage> getRecentSharedImagesFromInstagram(String code) {

        List<InstagramRecentMediaImage> list = null;

        try {
            list = build(code);
            logger.warn("gson recent images : {} ", new Gson().toJson(list));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("gson recent images error: {} ", e);
        }

        return list;
    }

    public List<InstagramRecentMediaImage> build(String code) throws Exception {

        if (InstagramConfig.clientSecret == null || InstagramConfig.clientId == null
                || InstagramConfig.clientUri == null) {
            throw new Exception("Please make sure that the"
                    + "clientId, clientSecret and redirectUri fields are set");
        }

        List<InstagramRecentMediaImage> responselist = null;
        HashMap<String, Object> postArgs = new HashMap<String, Object>();
        postArgs.put("client_id", InstagramConfig.clientId);
        postArgs.put("client_secret", InstagramConfig.clientSecret);
        postArgs.put("grant_type", "authorization_code");
        postArgs.put("redirect_uri", InstagramConfig.clientUri);

        postArgs.put("code", code);

        Gson gs = new Gson();
        RequestResponse response = new PostMethod().setPostParameters(postArgs)
                .setMethodURI(UriFactory.Auth.GET_ACCESS_TOKEN).call();
        try {
            InstagramConfig.accessToken = gs.fromJson(response.getResponseString(), AccessToken.class);

            Map<String, String> getArgs = new HashMap<String, String>();
            getArgs.put("access_token", InstagramConfig.accessToken.getAccess_token());
            getArgs.put("count", "6");

            String responseGet = new GetMethod().performRequest("https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN", getArgs);

            logger.warn("instagram recentmedia response : {}", responseGet);
            InstagramRecentMedia imageList = gs.fromJson(responseGet, InstagramRecentMedia.class);
            responselist = new ArrayList<>();
            for (InstagramRecentMediaData instagramRecentMediaImage : imageList.getData()) {
                instagramRecentMediaImage.getImages().setLink(instagramRecentMediaImage.getLink());
                responselist.add(instagramRecentMediaImage.getImages());
            }

        } catch (Exception e) {
            throw new Exception("JSON parsing error");
        }
        return responselist;
    }

    public void buildNewCodeForGettingImagesFromInstagram() throws Exception {
        if (InstagramConfig.clientId == null || InstagramConfig.clientUri == null) {
            throw new Exception("Please make sure that the "
                    + "clientId and redirectUri fields are set");
        }
        Map<String, String> args = new HashMap<>();
        args.put("client_id", InstagramConfig.clientId);
        args.put("redirect_uri", InstagramConfig.clientUri);
        args.put("response_type", "token");
        new GetMethod().performRequest(UriFactory.Auth.USER_AUTHORIZATION, args);
    }

    public void redirectLoginToInstagramForImages(String redirectLink) throws Exception {
        if (redirectLink == null) {
            throw new Exception("Please make sure that the redirect link is correct!");
        }
        Map<String, String> args = new HashMap<>();
        args.put("client_id", InstagramConfig.clientId);
        args.put("redirect_uri", InstagramConfig.clientUri);
        args.put("response_type", "token");
        new GetMethod().performRequest(UriFactory.Auth.USER_AUTHORIZATION, args);
    }
}



