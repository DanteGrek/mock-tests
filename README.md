# mock-tests

## Bugs:

#### Empty subjects in response

Actual request response:

    Request method:	POST
    Request URI:	http://metadata-server-mock.herokuapp.com/metadata/query
    Proxy:			<none>
    Request params:	<none>
    Query params:	<none>
    Form params:	<none>
    Path params:	<none>
    Headers:		Accept=*/*
    Content-Type=application/json
    Cookies:		<none>
    Multiparts:		<none>
    Body:
    {
    "subjects": [
        "3a65826707536cc1e39f28e0821ecdb4124f76dcad1f43e7f8592a1eb4344bb0",
        "cc6ff2a7c23f1f66afa003d47a549def3e839d13593b076aba4ea29b8c28e75a"
        ]
    }
    
    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 15
    X-Content-Type-Options: nosniff
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:12:48 GMT
    Via: 1.1 vegur
    
    {
    "subjects": [
        
        ]
    }

Expected response:

    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 15
    X-Content-Type-Options: nosniff
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:12:48 GMT
    Via: 1.1 vegur
    
    {
    "subjects": [
        "3a65826707536cc1e39f28e0821ecdb4124f76dcad1f43e7f8592a1eb4344bb0",
        "cc6ff2a7c23f1f66afa003d47a549def3e839d13593b076aba4ea29b8c28e75a"        
        ]
    }`

#### Incorrect status code and message when user send bad body.
This issue is a blocker for testing rest of get endpoints.  

Actual request and response:

    Request method:	POST
    Request URI:	http://metadata-server-mock.herokuapp.com/metadata/query
    Proxy:			<none>
    Request params:	<none>
    Query params:	<none>
    Form params:	<none>
    Path params:	<none>
    Headers:		Accept=*/*
    Content-Type=application/json
    Cookies:		<none>
    Multiparts:		<none>
    Body:
    {
    "topics": [
        "b45fb3f91d5494f5fed9f7a2cd2065dbe2ab02e034fe39f8add06366cbb054eb",
        "7406776a31dcb9f03c19fad8280576341fee086d0f85c6cc15e41296d36ceab4"
        ]
    }
    
    HTTP/1.1 500 Internal Server Error
    Connection: keep-alive
    Content-Type: text/html;charset=utf-8
    Content-Length: 30
    X-Xss-Protection: 1; mode=block
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:32:53 GMT
    Via: 1.1 vegur
    
    <html>
      <body>
        <h1>Internal Server Error</h1>
      </body>
    </html>

Expected response: 
    
    HTTP/1.1 400 Bad Request
    Connection: keep-alive
    Content-Type: text/html;charset=utf-8
    Content-Length: 30
    X-Xss-Protection: 1; mode=block
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:32:53 GMT
    Via: 1.1 vegur
    
    {
        "messages": ["Property: 'topics' is not supported."]
    }

#### Subjects are not created when endpoint returns successful status code.

Actual requests and responses:

    Request method:	POST
    Request URI:	http://metadata-server-mock.herokuapp.com/metadata/query
    Proxy:			<none>
    Request params:	<none>
    Query params:	<none>
    Form params:	<none>
    Path params:	<none>
    Headers:		Accept=*/*
    Content-Type=application/json
    Cookies:		<none>
    Multiparts:		<none>
    Body:
    {
    "subjects": [
    "b50bd3f922c67c854899aebb0bbb1edec34c682ceb7695d0b790683ef7dce028",
    "dbb186e0209a859dd92550173eee0fa544f438091d93ba44879061d5460e943d"
    ],
    "properties": [
    "name",
    "description",
    "url"
    ]
    }
    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 15
    X-Content-Type-Options: nosniff
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:45:36 GMT
    Via: 1.1 vegur
    
    {
    "subjects": [
    
        ]
    }
    Request method:	GET
    Request URI:	http://metadata-server-mock.herokuapp.com/metadata/b50bd3f922c67c854899aebb0bbb1edec34c682ceb7695d0b790683ef7dce028/properties/name
    Proxy:			<none>
    Request params:	<none>
    Query params:	<none>
    Form params:	<none>
    Path params:	<none>
    Headers:		Accept=*/*
    Content-Type=application/json
    Cookies:		<none>
    Multiparts:		<none>
    Body:			<none>
    
    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 94
    X-Content-Type-Options: nosniff
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:45:37 GMT
    Via: 1.1 vegur
    
    Requested subject 'b50bd3f922c67c854899aebb0bbb1edec34c682ceb7695d0b790683ef7dce028' not found

Expected response: 
    
    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 94
    X-Content-Type-Options: nosniff
    Server: WEBrick/1.4.2 (Ruby/2.6.6/2020-03-31)
    Date: Sat, 23 Jul 2022 14:45:37 GMT
    Via: 1.1 vegur

    {
    "value": "GoodCoin",
    "anSignatures": [
            {
                "publicKey": "eea2aece21d77ee1d363e997f535b10522ad7abd09a247553211c7eb51d9c37e",
                "signature": "35509234021b145f23d5ca53a2dd73701065f4327d05d0788519a3b16dce1e2030d0da2b9f67baea65c7e2bfd20de38c2228493fff53dea0ad00cc2e509be60b"
            }
        ]
    }