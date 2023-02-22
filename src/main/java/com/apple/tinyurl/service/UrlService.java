package com.apple.tinyurl.service;

import com.apple.tinyurl.dto.UrlDto;
import com.apple.tinyurl.exception.UrlNotFoundException;
import com.apple.tinyurl.model.UrlEntity;
import com.apple.tinyurl.repositry.UrlRepository;
import com.apple.tinyurl.util.Base62Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {

    //For distributed application we can maintain counter service as separate application or we can levarage master slave architecture like zookeeper  .
    private long counter  = 1;

    @Value("${short_url_domain}")
    private String shortUrlDomain;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    Base62Util base62Util;


    public UrlEntity createUrl(UrlDto urlDto){

        UrlEntity urlEntityFromDb = urlRepository.findByLongUrl(urlDto.getLongUrl());
       if(!ObjectUtils.isEmpty(urlEntityFromDb)){
           return urlEntityFromDb;
       }
       else{
           UrlEntity urlEntity = new UrlEntity();
           LocalDateTime createdDate = LocalDateTime.now();
           urlEntity.setLongUrl(urlDto.getLongUrl());
           urlEntity.setShortUrl(shortUrlDomain +"/"+ base62Util.toBase62(getRandomNumber()));
           urlEntity.setCreatedDate(createdDate);
           urlEntity.setExpiryDate(createdDate.plusSeconds(120));
           urlRepository.save(urlEntity);

           return urlEntity;
       }



    }

    public UrlEntity getUrlEntity(String  shortUrl){

        UrlEntity urlEntityFromDb = urlRepository.findByShortUrl(shortUrl);
        if(!ObjectUtils.isEmpty(urlEntityFromDb)){
            return urlEntityFromDb;
        }
        else{
           throw new UrlNotFoundException("Short URL not valid url");
        }

    }

    public long getRandomNumber(){
        Random rand = new Random();
        int upperbound = 7;
        return rand.nextLong();
    }

}
