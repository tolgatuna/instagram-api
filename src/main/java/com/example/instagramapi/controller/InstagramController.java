package com.example.instagramapi.controller;

import com.example.instagramapi.entities.InstagramConfig;
import com.example.instagramapi.util.GetMethod;
import com.example.instagramapi.util.UriFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class InstagramController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/auth/instagram", "/auth/instagram/"}, method = RequestMethod.GET, params = {"code"})
    public void instagramAuth(@RequestParam String code) {
        logger.warn("######### instagramAuth is executed! with code : " + code);
    }

    @RequestMapping("/test")
    public String callRequest() throws IOException {
        Map<String, String> args = new HashMap<>();
        args.put("client_id", InstagramConfig.clientId);
        args.put("redirect_uri", InstagramConfig.clientUri);
        args.put("response_type", "code");
        return new GetMethod().performRequest(UriFactory.Auth.USER_AUTHORIZATION, args);
    }

}
