package br.com.bcp.restfulws.filter;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    /**
     * Example of static filtering using JsonIgnore
     * @see SomeBean
     * @see JsonIgnore
     **/
    @GetMapping(path = "/filter/static")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value3", "value4", "value5");
    }

    /**
     * Example of static filtering using JsonIgnore
     * @see SomeBean
     * @see JsonIgnore
     **/
    @GetMapping(path = "/filter/static-list")
    public List<SomeBean> retrieveSomeBeanList() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3", "value4", "value5"),
                new SomeBean("value11", "value22", "value33", "value44", "value55"));
    }

    @GetMapping(path = "/filter/dynamic")
    public MappingJacksonValue retrieveOtherBean(
            @RequestParam(name = "fields", defaultValue = "field1, field2, field3") String... strFilters) {

        OtherBean otherBean = new OtherBean("value1", "value2", "value3");

        MappingJacksonValue mappingValue = doFilter(otherBean, strFilters);

        return mappingValue;
    }

    @GetMapping(path = "/filter/dynamic-list")
    public MappingJacksonValue retrieveOtherBeanList(
            @RequestParam(name = "fields", defaultValue = "field1, field2, field3") String... strFilters) {

        List<OtherBean> list = Arrays.asList(new OtherBean("value1", "value2", "value3"),
                new OtherBean("value11", "value22", "value33"));

        MappingJacksonValue mappingValue = doFilter(list, strFilters);

        return mappingValue;
    }

	private MappingJacksonValue doFilter(Object bean, String... strFilters) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(strFilters);
        FilterProvider filters = new SimpleFilterProvider().addFilter("OtherBeanFilter", filter);

        MappingJacksonValue mappingValue = new MappingJacksonValue(bean);
        mappingValue.setFilters(filters);
        return mappingValue;
    }

}