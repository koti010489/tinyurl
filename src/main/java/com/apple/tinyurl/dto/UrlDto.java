package com.apple.tinyurl.dto;

import com.sun.istack.NotNull;

public class UrlDto {
    @NotNull

    public String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
