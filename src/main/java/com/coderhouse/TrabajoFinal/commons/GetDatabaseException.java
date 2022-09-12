package com.coderhouse.TrabajoFinal.commons;

public class GetDatabaseException extends RuntimeException{
    public GetDatabaseException(String message) {
        super("No fue posible encontrar " + message);
    }
}
