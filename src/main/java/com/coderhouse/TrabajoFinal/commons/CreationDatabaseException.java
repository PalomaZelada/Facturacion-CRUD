package com.coderhouse.TrabajoFinal.commons;

public class CreationDatabaseException extends RuntimeException{
    public CreationDatabaseException(String message) {
        super("No fue posible crear " + message );
    }
}
