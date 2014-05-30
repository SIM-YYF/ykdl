package com.ykdl.net.msconverters;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by yuanwenfei on 2014/5/30.
 */
public class MByteArrayHttpMessageConverter extends AbstractHttpMessageConverter<byte[]> {
    /** Creates a new instance of the {@code ByteArrayHttpMessageConverter}. */
    public MByteArrayHttpMessageConverter() {
        super(new MediaType("application", "octet-stream"), MediaType.ALL);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return byte[].class.equals(clazz);
    }

    @Override
    public byte[] readInternal(Class<? extends byte[]> clazz, HttpInputMessage inputMessage) throws IOException {
        long contentLength = inputMessage.getHeaders().getContentLength();
        if (contentLength >= 0) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) contentLength);
            FileCopyUtils.copy(inputMessage.getBody(), bos);
            return bos.toByteArray();
        }
        else {
            return FileCopyUtils.copyToByteArray(inputMessage.getBody());
        }
    }

    @Override
    protected Long getContentLength(byte[] bytes, MediaType contentType) {
        return (long) bytes.length;
    }

    @Override
    protected void writeInternal(byte[] bytes, HttpOutputMessage outputMessage) throws IOException {
        FileCopyUtils.copy(bytes, outputMessage.getBody());
    }
}
