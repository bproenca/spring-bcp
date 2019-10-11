package br.com.bcp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
As you can see, this is a simple Java class with a handful of properties and matching getter methods.
It’s annotated with @JsonIgnoreProperties from the Jackson JSON processing library to indicate
that any properties not bound in this type should be ignored.
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;

    private Value value;

    public Quote() {
    }

    public String getType() {
	return type;
    }

    public Value getValue() {
	return value;
    }

    public void setType(String type) {
	this.type = type;
    }

    public void setValue(Value value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "Quote{" +
			"type='" + type + '\'' +
			", value=" + value +
			'}';
    }
}