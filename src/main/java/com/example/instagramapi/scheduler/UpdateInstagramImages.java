package com.example.instagramapi.scheduler;

import com.example.instagramapi.services.PageContentInstagramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UpdateInstagramImages {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    PageContentInstagramService instagramService;

//    /@Scheduled(fixedDelay = 60000)
    public void updateImages() {
        logger.warn("Images are updated. Time:  {}", dateFormat.format(new Date()));
        System.out.println("Images are updated. Time : " + dateFormat.format(new Date()));
        String response = "";

        try {
            instagramService.buildNewCodeForGettingImagesFromInstagram();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Images service failed. Time:  {}", dateFormat.format(new Date()));
        }
    }
}
