package com.apple.tinyurl.controller;


import com.apple.tinyurl.dto.UrlDto;
import com.apple.tinyurl.model.UrlEntity;
import com.apple.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1")
@CrossOrigin
public class UrlController {
    @Autowired
    UrlService urlService;
    @PostMapping(value = "/createShortUrl")
    public ResponseEntity<UrlEntity> createShortUrl(@RequestBody  UrlDto urlDto){
        return new ResponseEntity<>(urlService.createUrl(urlDto), HttpStatus.CREATED);

    }
    @GetMapping(value = "/longurl")
    public ResponseEntity<UrlEntity> getLongUrl(@RequestParam("shorturl") @NonNull String shorturl ){
        return new ResponseEntity<>(urlService.getUrlEntity(shorturl), HttpStatus.OK);

    }
}
