package org.institutsaintjean.intervention.gestionDesInterventions.Entities;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;;


public class ByteArrayDataSource implements DataSource{
    private byte[] data;
    private String type;

   public ByteArrayDataSource(byte[] data, String type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("Not Supported");
    }

    @Override
    public String getContentType() {
        return type;
    }

    @Override
    public String getName() {
        return "ByteArrayDataSource";
    }
}

