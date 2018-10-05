package com.example.instagramapi.entities;


import com.example.instagramapi.util.AccessToken;
import org.apache.http.cookie.Cookie;

import java.io.Serializable;
import java.util.List;

public class InstagramConfig implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7852568222214833905L;

    // For local Hosts :
    public static String clientUri = "http://localhost:8080/auth/instagram";
//	public static String clientUri = "http://localhost:9080/Gradle___com_ets_mice_webapp_jsp_war__exploded_/auth/instagram";

    // For Test link :
//	public static String clientUri = "http://10.0.108.41/auth/instagram";

    // For Live link :
//	public static String clientUri = "http://www.etsmice.com.tr/auth/instagram";

    public static String accessTokenUri = "https://api.instagram.com/oauth/access_token";
    public static String authUri = "https://www.instagram.com/oauth/authorize/";
    public static String clientId = "045120c67829477fa9b607ba9483625d";//"9d73e1e52730428f93fd7912f08d55cf";
    public static String clientSecret = "4e831c8e4f5c4d55a47bfe5d006546d7";//"e823a26f7ecd4f03a7625b0ca29950ae";
    public static String scope = "basic";
    public static AccessToken accessToken;
    public static boolean hasProxy = false;
    public static String proxyAddress = "10.0.0.2";
    public static int proxyPort = 8081;
    public static String instagramUser = "etsmice";
    public static String instagramPass = "mice?1414";

    public static List<Cookie> cookies;
}
