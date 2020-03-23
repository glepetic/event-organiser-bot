package org.lepetic.telegrambot.spotify;

public enum HTTPStatus {

    OK(200),
    RESOURCE_CREATED(201);

    private int code;

    private HTTPStatus(int code){
        this.code = code;
    }

    public int code(){
        return this.code;
    }

}
