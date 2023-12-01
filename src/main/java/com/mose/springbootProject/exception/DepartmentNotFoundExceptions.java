package com.mose.springbootProject.exception;

public class DepartmentNotFoundExceptions extends Exception{

    public DepartmentNotFoundExceptions() {
        super();
    }

    public DepartmentNotFoundExceptions(String message) {
        super(message);
    }

    public DepartmentNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNotFoundExceptions(Throwable cause) {
        super(cause);
    }

    protected DepartmentNotFoundExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}