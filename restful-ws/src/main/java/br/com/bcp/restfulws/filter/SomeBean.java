package br.com.bcp.restfulws.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"field1", "field4"})
public class SomeBean {

    private String field1;
    private String field2;
    @JsonIgnore
    private String field3;
    private String field4;
    private String field5;

    protected SomeBean() {

    }

    /**
     * @return the field5
     */
    public String getField5() {
        return field5;
    }

    /**
     * @param field5 the field5 to set
     */
    public void setField5(String field5) {
        this.field5 = field5;
    }

    public SomeBean(String string1, String string2, String string3, String string4, String string5) {
        field1 = string1;
        field2 = string2;
        field3 = string3;
        field4 = string4;
        field5 = string5;
    }

    /**
     * @return the field1
     */
    public String getField1() {
        return field1;
    }

    /**
     * @param field1 the field1 to set
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * @return the field2
     */
    public String getField2() {
        return field2;
    }

    /**
     * @param field2 the field2 to set
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

    /**
     * @return the field3
     */
    public String getField3() {
        return field3;
    }

    /**
     * @param field3 the field3 to set
     */
    public void setField3(String field3) {
        this.field3 = field3;
    }

    /**
     * @return the field4
     */
    public String getField4() {
        return field4;
    }

    /**
     * @param field4 the field4 to set
     */
    public void setField4(String field4) {
        this.field4 = field4;
    }

}
