package com.myBlogNest.payload;




import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {


    private long id;

    @NotEmpty
    @Size(min=2, message = "post should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min =10, message = "Post Description should have at list 10 character")
    private String description;

    @NotEmpty
    private String content;

}
