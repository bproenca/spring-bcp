package br.com.bcp.restfulws.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("OtherBeanFilter")
public class OtherBean {

    private String field1;
    private String field2;
    private String field3;

    protected OtherBean() {

    }

    public OtherBean(String string1, String string2, String string3) {
        field1 = string1;
        field2 = string2;
        field3 = string3;
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
}
