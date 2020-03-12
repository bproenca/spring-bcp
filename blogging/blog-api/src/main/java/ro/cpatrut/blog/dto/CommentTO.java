package ro.cpatrut.blog.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static ro.cpatrut.blog.dto.ApiModelPropertyValues.FOREIGN_KEY_MESSAGE;
import static ro.cpatrut.blog.dto.ApiModelPropertyValues.ONLY_FOR_GET_MESSAGE;

@ApiModel(description = "Class representing a post on the blog")
@Value
@AllArgsConstructor
public class CommentTO {

    @ApiModelProperty(value = ApiModelPropertyValues.UUID_MESSAGE)
    private final UUID id;

    @ApiModelProperty(position = 1, required = true)
    @Size(min = 5, max = 255)
    private final String title;

    @ApiModelProperty(position = 2, value = ONLY_FOR_GET_MESSAGE)
    @NotNull
    private final String author;

    @ApiModelProperty(position = 3, required = true)
    @Size(min = 10)
    @NotNull
    private final String content;

    @ApiModelProperty(position = 5, value = ONLY_FOR_GET_MESSAGE)
    private final String updateTime;

    @ApiModelProperty(position = 4, value = ONLY_FOR_GET_MESSAGE)
    private final String creationTime;

    @ApiModelProperty(position = 6, value = FOREIGN_KEY_MESSAGE)
    private final UUID postId;

    @Builder
    private static CommentTO newPostTo(final UUID id,
                                       final String title,
                                       final String author,
                                       final String content,
                                       final String updateTime,
                                       final String creationTime,
                                       final UUID postId
    ) {
        return new CommentTO(id, title, author, content, updateTime, creationTime, postId);
    }

}
