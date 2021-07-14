package com.sfzjh;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:28
 * @PackageName  com.sfzjh
 * @Name  NotFoundException
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
