package br.com.bcp.restfulws.versioning;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    /**
     * URI Versioning (used by Twitter)
     * <ul>
     * <li>(+) Caching</li>
     * <li>(+) Can execute the request on the browser</li>
     * <li>(+) Easy API Documentation (e.g. Swagger)</li>
     * <li>(-) URI Pollution</li>
     * </ul>
     * <br>
     * 
     * @return {@link PersonV1}
     */
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    /**
     * Request Parameter Versioning (used by Amazon)
     * <ul>
     * <li>(+) Caching</li>
     * <li>(+) Can execute the request on the browser</li>
     * <li>(+) Easy API Documentation (e.g. Swagger)</li>
     * <li>(-) URI Pollution</li>
     * </ul>
     * <br>
     * 
     * @return {@link PersonV1}
     */
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(value = "/person/param", params = "version=3")
    public Map<String, Object> paramV3(@RequestParam(defaultValue = "world") String param) {
        Map<String, Object> map = new HashMap<>();
        map.put("greeting", String.format("hello %s", param));
        map.put("person", new PersonV2(new Name("Bob", "Charlie")));
        return map;
    }

    /**
     * (Custom) headers versioning (used by Microsoft)
     * <ul>
     * <li>(+) Clean URI</li>
     * <li>(-) Misuse of HTTP Headers</li>
     * <li>(-) No or Difficult Caching (same URL/Query String)</li>
     * <li>(-) Can't execute the request on the browser</li>
     * <li>(-) Difficult API Documentation</li>
     * </ul>
     * <br>
     * 
     * @return {@link PersonV1}
     */
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    /**
     * Media type versioning (a.k.a “content negotiation” or “accept header”) -
     * (used by GitHub)
     * <ul>
     * <li>(+) Clean URI</li>
     * <li>(-) Misuse of HTTP Headers</li>
     * <li>(-) No or Difficult Caching (same URL/Query String)</li>
     * <li>(-) Can't execute the request on the browser</li>
     * <li>(-) Difficult API Documentation</li>
     * </ul>
     * <br>
     * 
     * @return {@link PersonV1}
     */
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

}