
to test from httpie
```bash
http GET http://localhost:8099/hello OAuth:something
```

### What Spring Security offers by default?

Secure HTTP Headers
If you compare the HTTP headers before and after adding the Spring Security, you’ll see that the framework has added some new headers:

```
Cache-Control:no-cache, no-store, max-age=0, must-revalidate
Expires:0
Pragma:no-cache
X-Content-Type-Options:nosniff
X-Frame-Options:DENY
X-XSS-Protection:1; mode=block
Let’s see what Spring Security brings to the party:
```

The Cache-Control, Expires and Pragma headers disable the caching
The X-Content-Type-Options header disables content sniffing for the browser and provides protection from the MIME type confusion attack
The X-Frame-Options header prevents clickjacking attack
The X-XSS-Protection header protects the user from the cross-site scripting attack

### Ref

[Spring_security_doc](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/)