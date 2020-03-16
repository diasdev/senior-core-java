package br.com.senior.core.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

/**
 * Exceção de Serviço
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends Exception {

    int errorCode;

    /**
     * Construtor
     *
     * @param errorCode
     * @param message
     */
    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Construtor
     *
     * @param message
     * @param e
     */
    public ServiceException(String message, IOException e) {
        super(message, e);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                (errorCode != 0 ? ("errorCode=" + errorCode) : "") +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
