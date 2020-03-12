[Complete Guide on How to Build APIs With Spring](
https://medium.com/swlh/complete-guide-on-how-to-build-apis-with-spring-9c2566d34c4c)

### How to test (Swagger)

* docker-compose up (to start the database)
* Run spring project (BlogApi.java)
* http://localhost:8080/swagger-ui.html#/post-controller
* Post "/posts"  
`
{
  "title": "post title two",
  "author": "bruno proenca",
  "content": "string 123456789"
}
`
* Get "/posts/all"  
`
curl -X GET "http://localhost:8080/posts/all?page=0&size=2" -H "accept: */*"
`