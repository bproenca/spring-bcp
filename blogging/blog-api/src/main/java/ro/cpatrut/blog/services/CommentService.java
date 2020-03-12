package ro.cpatrut.blog.services;

import org.springframework.data.domain.Pageable;
import ro.cpatrut.blog.dto.CommentTO;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentTO save(final CommentTO comment);

    List<CommentTO> getByPostId(final UUID postId, Pageable pageable);

    void update(final CommentTO comment);

    void delete(final UUID id);
}
