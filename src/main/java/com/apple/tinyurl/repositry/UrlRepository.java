package com.apple.tinyurl.repositry;

import com.apple.tinyurl.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {

    public UrlEntity findByLongUrl(String longUrl);

    public UrlEntity findByShortUrl(String shortUrl);

    @Query(nativeQuery = true, value = "SELECT currval('url_entity_sequence')")
    Long getEntitySequence() ;
}
