package com.apple.tinyurl.service;

import com.apple.tinyurl.dto.UrlDto;
import com.apple.tinyurl.model.UrlEntity;
import com.apple.tinyurl.repositry.UrlRepository;
import com.apple.tinyurl.util.Base62Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @InjectMocks
    UrlService urlService;
    @Mock
    UrlRepository urlRepository;

    @Mock
    Base62Util base62Util;
    @Test
    public void createUrl_Url_Present_DB(){
        UrlDto urlDto = new UrlDto();
        urlDto.setLongUrl("longUrl");
        UrlEntity urlEntity = new UrlEntity();
        Mockito.when(urlRepository.findByLongUrl(urlDto.getLongUrl())).thenReturn(urlEntity);
        UrlEntity urlEntityReturn = urlService.createUrl(urlDto);
        assertEquals(urlEntity, urlEntityReturn);
    }

    @Test
    public void createUrl_Url_NotPresent_DB() throws NoSuchFieldException {

        Field field =urlService.getClass().getDeclaredField("shortUrlDomain");
        field.setAccessible(true);
       // ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field,urlService,"localhost:9080");
        UrlDto urlDto = new UrlDto();
        String shortenedString = "shortenedString";
        urlDto.setLongUrl("longUrl");
        UrlEntity urlEntity = new UrlEntity();
        Mockito.when(urlRepository.findByLongUrl(urlDto.getLongUrl())).thenReturn(null);
        Mockito.when(urlRepository.save(Mockito.any(UrlEntity.class))).thenReturn(urlEntity);
        Mockito.when(base62Util.toBase62(Mockito.anyLong())).thenReturn(shortenedString);
        UrlEntity urlEntityReturn = urlService.createUrl(urlDto);
        assertEquals("localhost:9080/shortenedString", urlEntityReturn.getShortUrl());
    }
}
