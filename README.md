# wicketstuff-restannotation-TEST


Adding wicketstuff-restannotations to a grails project using wicket via the grails-wicket plugin
https://ci.apache.org/projects/wicket/guide/6.x/guide/wicketstuff.html#wicketstuff_6

**Uses Example Code from here:**
https://github.com/wicketstuff/core/tree/master/wicketstuff-restannotations-parent

**REQUIREMENTS:**

_Requires latest grails-wicket plugin upgraded to wicket 6.28.0..** package and install it manually_

https://github.com/narenlog/grails-wicket

_Instructions on how to add wicketstuff-restannotations library to grails-wicket plugin :_

BuildConfig.groovy of grails-wicket plugin adds dependency for wicketstuff-restannotations
https://grailslog.wordpress.com/2018/06/01/how-to-add-an-external-library-or-jar-file-that-is-not-a-grails-plugin-to-your-grails-project/

**HOW TO TEST:**

_FROM UI_

Access http://localhost:8080 to view Index.html

Access http://localhost:8080/personsmanager/persons to view json output of Index.html


_COMMANDLINE TESTING OF GET, POST, DELETE_

```
curl -i -X GET http://localhost:8080/personsmanager/persons
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 07:11:20 GMT

[{"name":"Freddie Mercury","email":"fmercury@queen.com","password":"Eeehooo!"},{"name":"John Deacon","email":"jdeacon@queen.com","password":"bass"},{"name":"Brian May","email":"bmay@queen.com","password":"guitar"},{"name":"Roger Taylor","email":"rtaylor@queen.com","password":"drum"}]
```

```
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"etst","email":"test@test.com","password":"test!"}' http://localhost:8080/personsmanager/persons
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 07:32:02 GMT

{"name":"etst","email":"test@test.com","password":"test!"}
```

```
curl -i -X DELETE http://localhost:8080/personsmanager/persons/0
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 06:51:29 GMT
```

