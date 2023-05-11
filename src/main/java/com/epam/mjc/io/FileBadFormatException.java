package com.epam.mjc.io;
import java.io.IOException;

public class FileBadFormatException extends IOException {
    public FileBadFormatException(String a) {
        super(a);
    }
}
