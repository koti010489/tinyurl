# Tiny URL Generation

## Contents of this file

 - Introduction
 - Running Application In Local
- Maintainers
## Introduction

As TIny URL application we need to develop follwing APIS.
- When user enters first times longurl for website we need to create shorturl and persist into database.
- When user enters same long url for website get short url from data base and return it.
- API to fetch longurl when user passes shorturl.
- Tinyurl application will generate shorturl in below format localhost:9080/{7alphanumeric} 
##  Running Application In Local
- Navigate to workspace in command prompt
- Execute below commands  ./gradlew bootRun
- Verify application is up and running in port 9080

## Testing APIS
- From PostMan invoke below posturl http://localhost:9080/createShortUrl with body to create shortversion of url
{
	"longUrl": "www.apple.com"
}
- output looks something like this

{
    "id": 1,
    "longUrl": "www.apple.com",
    "shortUrl": "localhost:9080/cLyqEYAkGqv",
    "createdDate": "2023-02-22T22:29:35.636045",
    "expiryDate": "2023-02-22T22:31:35.636045"
}

- From PostMan invoke below geturl  http://localhost:9080/longurl?shorturl=localhost:9080/ggqnC4fHd34 

- output looks something like this

{
    "id": 1,
    "longUrl": "www.apple.com",
    "shortUrl": "localhost:9080/cLyqEYAkGqv",
    "createdDate": "2023-02-22T22:29:35.636045",
    "expiryDate": "2023-02-22T22:31:35.636045"
}

Current maintainers:
- Yenumula Koti Reddy
