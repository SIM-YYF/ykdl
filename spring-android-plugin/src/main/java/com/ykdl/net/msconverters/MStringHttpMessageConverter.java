package com.ykdl.net.msconverters;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanwenfei on 2014/5/27.
 */
public class MStringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

    private final Charset defaultCharset;

    private final List<Charset> availableCharsets;

    private boolean writeAcceptCharset = true;

    /**
     * Create a new StringHttpMessageConverter instance with a default {@link Charset} of ISO-8859-1,
     * and default list of available {@link Charset}'s from {@link Charset#availableCharsets()}.
     */
    public MStringHttpMessageConverter() {
        this(Charset.forName("ISO-8859-1"));
    }

    /**
     * Create a new StringHttpMessageConverter instance with a default {@link Charset},
     * and default list of available {@link Charset}'s from {@link Charset#availableCharsets()}.
     * @param defaultCharset the Charset to use
     */
    public MStringHttpMessageConverter(Charset defaultCharset) {
        this(defaultCharset, new ArrayList<Charset>(Charset.availableCharsets().values()));
    }

    /**
     * Create a new StringHttpMessageConverter instance with a default {@link Charset},
     * and list of available {@link Charset}'s.
     * @param defaultCharset the Charset to use
     * @param availableCharsets the list of available Charsets
     */
    public MStringHttpMessageConverter(Charset defaultCharset, List<Charset> availableCharsets) {
        super(new MediaType("text", "plain", defaultCharset), MediaType.ALL);
        this.defaultCharset = defaultCharset;
        this.availableCharsets = availableCharsets;
    }

    /**
     * The default {@link Charset} is ISO-8859-1. Can be overridden in subclasses,
     * or through the use of the alternate constructor.
     * @return default Charset
     */
    public Charset getDefaultCharset() {
        return this.defaultCharset;
    }

    /**
     * Indicates whether the {@code Accept-Charset} should be written to any outgoing request.
     * <p>
     * Default is {@code true}.
     */
    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
    }

    @Override
    protected Long getContentLength(String s, MediaType contentType) {
        Charset charset = getContentTypeCharset(contentType);
        try {
            return (long) s.getBytes(charset.displayName()).length;
        } catch (UnsupportedEncodingException ex) {
            // should not occur
            throw new InternalError(ex.getMessage());
        }
    }

    @Override
    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        if (writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }

    /**
     * Return the list of supported {@link Charset}.
     *
     * <p>
     * By default, returns {@link Charset#availableCharsets()}. Can be overridden in subclasses,
     * or through the use of the alternate constructor.
     *
     * @return the list of accepted charsets
     */
    protected List<Charset> getAcceptedCharsets() {
        return this.availableCharsets;
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            return contentType.getCharSet();
        } else {
            return getDefaultCharset();
        }
    }

}

