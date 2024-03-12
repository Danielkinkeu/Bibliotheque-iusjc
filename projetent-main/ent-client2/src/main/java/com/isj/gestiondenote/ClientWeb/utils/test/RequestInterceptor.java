package com.isj.gestiondenote.ClientWeb.utils.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class RequestInterceptor {

    public static void addHeaders(HttpHeaders headers, HttpSession session) {
        String accessToken = session.getAttribute("accessToken").toString();
        System.out.println(accessToken);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.set("Authorization", accessToken);
    }

}
