package com.coderhouse.TrabajoFinal.commons;

public class DeleteDatabaseException extends RuntimeException{
    public DeleteDatabaseException(String message) {
        super("No fue posible eliminar " + message);
    }
}
