package hello;

/*
 * To model the greeting representation, you create a resource representation class.
 * Provide a plain old java object with fields, constructors, and accessors for the id and content data:
 */
public class Greeting {

    private final String content;

    private final long id;

    public Greeting(long id, String content) {
	this.id = id;
	this.content = content;
    }

    public String getContent() {
	return content;
    }

    public long getId() {
	return id;
    }
}
