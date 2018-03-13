package com.github.jrry.productparser.converters;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class ImageToBase64 {
    private URL url;

    public ImageToBase64(String URL) throws MalformedURLException {
        this.url = new URL(URL.startsWith("//") ? "http:" + URL : URL);
    }

    public String getImageInBase64() throws IOException {
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
