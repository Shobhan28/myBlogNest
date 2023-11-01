package com.myBlogNest.exception;

    public class ResourceNotFoundException extends RuntimeException{

        //This method is called through streamApi in the ServiceImpl class with a exception string message
        public ResourceNotFoundException(String msg) {
            super(msg);
        }
    }

