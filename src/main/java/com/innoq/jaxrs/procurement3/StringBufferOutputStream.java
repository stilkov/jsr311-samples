package com.innoq.jaxrs.procurement3;

import java.io.OutputStream;

public class StringBufferOutputStream extends OutputStream {
    private StringBuffer buffer;

    public StringBufferOutputStream() {
        this(new StringBuffer());
    }

    public StringBufferOutputStream(StringBuffer buffer) {
        this.buffer = buffer;

    }

    public void write(int b) {
        buffer.append((char) b);
    }


    public void flush() {
    }


    public String toString(){
        return buffer.toString();
    }
}
