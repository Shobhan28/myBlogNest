package com.myBlogNest.payload;




import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {


    private long id;

    @NotEmpty
    @Size(min=5,max=30, message = "post should have at least 2 characters " +
                                      "but should not exceed 30 chars")
    private String title;
    @NotEmpty
    @Size(min =10,max = 100,message = "Post Description should have at list 10 character" +
                                           " but should not exceed 100 chars")
    private String description;

    @NotBlank
    private String content;

}
