package com.rkm.beprepared.exception;

public class EntityNotFoundException extends BadRequestException{
    public EntityNotFoundException(String message){
        super(message);
    }
}
