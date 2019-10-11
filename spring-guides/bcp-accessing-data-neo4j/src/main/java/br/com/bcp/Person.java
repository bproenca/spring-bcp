package br.com.bcp;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
The Person class is annotated @NodeEntity. When Neo4j stores it, it results in the
creation of a new node. This class also has an id marked @GraphId.
Neo4j uses @GraphId internally to track the data.

The next important piece is the set of teammates. It is a simple Set<Person>,
but marked up as @Relationship. This means that every member of this set is
expected to also exist as a separate Person node. Note how the direction is
set to UNDIRECTED. This means that when you query the TEAMMATE relationship,
Spring Data Neo4j will ignore the direction of the relationship.

With the worksWith() method, you can easily link people together.

Finally, you have a convenient toString() method to print out the person’s
name and that person’s co-workers.
 */
@NodeEntity
public class Person {

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
    public Set<Person> teammates;

    @GraphId private Long id;

    private String name;

    ;

    public Person(String name) {
	this.name = name;
    }

    private Person() {
	// Empty constructor required as of Neo4j API 2.0.5
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String toString() {
	return this.name + "'s teammates => "
			+ Optional.ofNullable(this.teammates).orElse(
			Collections.emptySet())
			.stream()
			.map(
					person -> person.getName()).collect(Collectors.toList());
    }

    public void worksWith(Person person) {
	if (teammates == null) {
	    teammates = new HashSet<>();
	}
	teammates.add(person);
    }
}