package br.com.bcp;

import org.springframework.data.neo4j.repository.GraphRepository;

/*
Spring Data Neo4j is focused on storing data in Neo4j. But it inherits functionality from
the Spring Data Commons project, including the ability to derive queries. Essentially,
you don’t have to learn the query language of Neo4j, but can simply write a handful of
methods and the queries are written for you.

PersonRepository extends the GraphRepository interface and plugs in the type it
operates on: Person. Out-of-the-box, this interface comes with many operations,
including standard CRUD (create-read-update-delete) operations.

But you can define other queries as needed by simply declaring their method signature.
In this case, you added findByName, which seeks nodes of type Person and finds the one
that matches on name. You also have findByTeammatesName, which looks for a Person
node, drills into each entry of the teammates field, and matches based on the
teammate’s name.
 */
public interface PersonRepository extends GraphRepository<Person> {

    Person findByName(String name);
}