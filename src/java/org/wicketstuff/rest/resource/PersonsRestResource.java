
package org.wicketstuff.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.annotations.parameters.RequestBody;
import org.wicketstuff.rest.annotations.parameters.ValidatorKey;
import org.wicketstuff.rest.contenthandling.json.objserialdeserial.GsonObjectSerialDeserial;
import org.wicketstuff.rest.contenthandling.json.webserialdeserial.JsonWebSerialDeserial;
import org.wicketstuff.rest.domain.PersonPojo;
import org.wicketstuff.rest.utils.http.HttpMethod;


/*   COMMANDLINE TESTING OF GET, POST, DELETE


curl -i -X GET http://localhost:8080/personsmanager/persons
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 07:11:20 GMT

[{"name":"Freddie Mercury","email":"fmercury@queen.com","password":"Eeehooo!"},{"name":"John Deacon","email":"jdeacon@queen.com","password":"bass"},{"name":"Brian May","email":"bmay@queen.com","password":"guitar"},{"name":"Roger Taylor","email":"rtaylor@queen.com","password":"drum"}]

curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"etst","email":"test@test.com","password":"test!"}' http://localhost:8080/personsmanager/persons
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 07:32:02 GMT

{"name":"etst","email":"test@test.com","password":"test!"}


curl -i -X DELETE http://localhost:8080/personsmanager/persons/0
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 01 Jun 2018 06:51:29 GMT



 */
public class PersonsRestResource extends AbstractRestResource<JsonWebSerialDeserial> {
    private final List<PersonPojo> persons = new ArrayList<PersonPojo>();

    public PersonsRestResource() {
        super(new JsonWebSerialDeserial(new GsonObjectSerialDeserial()));

        persons.add(new PersonPojo("Freddie Mercury", "fmercury@queen.com", "Eeehooo!"));
        persons.add(new PersonPojo("John Deacon", "jdeacon@queen.com", "bass"));
        persons.add(new PersonPojo("Brian May", "bmay@queen.com", "guitar"));
        persons.add(new PersonPojo("Roger Taylor", "rtaylor@queen.com", "drum"));
    }

    @MethodMapping("/persons")
    public List<PersonPojo> getAllPersons() {
        return persons;
    }

    @MethodMapping(value = "/persons/{personIndex}", httpMethod = HttpMethod.DELETE)
    public void deletePerson(int personIndex) {
        persons.remove(personIndex);
    }

    @MethodMapping(value = "/persons", httpMethod = HttpMethod.POST)
    public PersonPojo createPerson(
            @ValidatorKey("personValidator") @RequestBody PersonPojo personPojo) {
        persons.add(personPojo);
        return personPojo;
    }

    @Override
    protected void onInitialize(JsonWebSerialDeserial objSerialDeserial) {
        super.onInitialize(objSerialDeserial);
        registerValidator("personValidator", new PersonPojoValidator());
    }
}