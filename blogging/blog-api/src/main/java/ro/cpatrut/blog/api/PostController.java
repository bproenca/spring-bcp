package ro.cpatrut.blog.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.cpatrut.blog.dto.PostTO;
import ro.cpatrut.blog.services.PostService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PostController.PATH)
@Slf4j
public class PostController {

    final static String PATH = "posts";

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostTO> save(@Valid @RequestBody final PostTO post) {
        log.debug("saving  post: " + post);
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody final PostTO post) {
        log.debug("updating post: " + post);
        postService.update(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PostTO> getById(@NotNull @RequestParam("id") final UUID id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostTO>> getAll(@NotNull @RequestParam("page") final int page,
                                               @NotNull @RequestParam("size") final int size) {
        return new ResponseEntity<>(postService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestParam("id") final UUID id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
