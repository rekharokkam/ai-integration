package com.learning.basics;

public class SealedInterfaceSystemLoggerExample {

    public final System.Logger log = System.getLogger(SealedInterfaceSystemLoggerExample.class.getName());

    public sealed interface Employee permits Supervisor, Engineer {}

    public record Supervisor (long employeeId, String name, int yearsOfService)
        implements Employee{}

    public record Engineer (long employeeId, String name, int yearsOfService)
        implements Employee {}

    public void addEmployee (Employee employee) {
        switch (employee) {
            case Supervisor supervisor ->
                log.log(System.Logger.Level.INFO, "this is the Supervisor class");
            case Engineer engineer ->
                log.log(System.Logger.Level.INFO, "this is the employee class");
        }
    }
}
