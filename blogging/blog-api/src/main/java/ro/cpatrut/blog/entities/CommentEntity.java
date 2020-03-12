package ro.cpatrut.blog.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(schema = "blog", name = "comments")
@Data
public class CommentEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "update_time")
    @UpdateTimestamp
    private ZonedDateTime updateTime;

    @Column(name = "creation_time", nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationTime;

    @Column(name = "post_id", nullable = false, updatable = false)
    private UUID postId;
}
