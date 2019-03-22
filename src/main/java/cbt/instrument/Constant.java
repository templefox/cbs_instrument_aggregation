package cbt.instrument;

import java.time.format.DateTimeFormatter;

public abstract class Constant {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
