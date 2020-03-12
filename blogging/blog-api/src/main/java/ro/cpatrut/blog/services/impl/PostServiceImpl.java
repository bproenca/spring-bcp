package ro.cpatrut.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.cpatrut.blog.dto.PostTO;
import ro.cpatrut.blog.entities.PostEntity;
import ro.cpatrut.blog.repositories.PostRepository;
import ro.cpatrut.blog.services.PostService;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(final ModelMapper modelMapper, final PostRepository postRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    @Override
    public PostTO save(final PostTO post) {
        final PostEntity entity = modelMapper.map(post, PostEntity.class);
        entity.setId(UUID.randomUUID());
        return modelMapper.map(postRepository.save(entity), PostTO.PostTOBuilder.class)
                .build();
    }

    @Override
    public PostTO getById(final UUID id) {
        final PostEntity entity = postRepository.getById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(entity, PostTO.PostTOBuilder.class).build();
    }

    @Override
    public List<PostTO> findAll(final Pageable pageable) {
        return postRepository.findAll(pageable).get()
                .map(postEntity -> modelMapper.map(postEntity, PostTO.PostTOBuilder.class).build())
                .collect(Collectors.toList());
    }

    @Override
    public void update(final PostTO post) {
        postRepository.updatePost(post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                ZonedDateTime.now(),
                post.getId());
    }

    @Override
    public void delete(final UUID id) {
        postRepository.deleteById(id);
    }
}
