package main.command;

import main.exception.InvalidFieldException;

public abstract class FieldManager {
    public abstract DukeField getField(String field) throws InvalidFieldException;
}
