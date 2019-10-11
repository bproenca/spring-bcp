-- Check data: http://localhost:8080/h2-console
-- JDBC URL: jdbc:h2:mem:testdb
insert into user values(1001, sysdate(), 'AB');
insert into user values(1002, sysdate(), 'Jill');
insert into user values(1003, sysdate(), 'Jam');

insert into post values(11001, 'My First Post',  1001);
insert into post values(11002, 'My Second Post', 1001);