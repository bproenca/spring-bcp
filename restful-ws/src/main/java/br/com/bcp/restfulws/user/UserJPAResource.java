package br.com.bcp.restfulws.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
		return user.get().getPosts();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		try {
			userRepo.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			System.out.println(ex.getMessage());
			throw new UserNotFoundException("id - " + id, ex);
		}
		;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepo.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createUserPost(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		post.setUser(user.get());
		Post savedPost = postRepo.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{id}/posts/{postId}")
	public Resource<Post> retrieveUserPost(@PathVariable int id, @PathVariable int postId) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		for (Post post: user.get().getPosts()) {
			if (post.getId().equals(postId)) {
				Resource<Post> resource = new Resource<Post>(post);
				ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveUserPosts(id));
				resource.add(linkTo.withRel("all-user-posts"));

				return resource;
			}
		}

		return null;
	}


}