package com.apple.tinyurl.controller;

import com.apple.tinyurl.dto.UrlDto;
import com.apple.tinyurl.model.UrlEntity;
import com.apple.tinyurl.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {
    @InjectMocks
    UrlController urlController;
    @Mock
    UrlService urlService;

    @Test
    public void createShortUrl() {
        UrlDto urlDto = new UrlDto();
        String longUrl = urlDto.getLongUrl();
        UrlEntity urlEntity = new UrlEntity();
        Mockito.when(urlService.createUrl(urlDto)).thenReturn(urlEntity);
        ResponseEntity<UrlEntity> responseEntity = urlController.createShortUrl(urlDto);
        assertEquals(responseEntity.getBody(), urlEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }
}
