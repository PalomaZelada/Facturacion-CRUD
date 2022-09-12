package com.coderhouse.TrabajoFinal.commons;

public class UpdateDatabaseException extends RuntimeException{
    public UpdateDatabaseException(String message) {
        super("No fue posible actualizar " + message);
    }
}
