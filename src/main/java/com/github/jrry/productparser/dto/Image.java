package com.github.jrry.productparser.dto;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class Image {
    private URL url;

    private final Logger logger = LoggerFactory.getLogger(Image.class);

    public Image(String URL) {
        try {
            this.url = new URL(URL.startsWith("//") ? "http:" + URL : URL);
        } catch (MalformedURLException e) {
            logger.error(e.getClass().getName() + " - " + e.getMessage());
        }
    }

    public String getImageURL() {
        return url.toString();
    }

    public URL getUrl() {
        return url;
    }

    public String getImageInBase64() {
        try {
            InputStream is = url.openStream();
            byte[] bytes = IOUtils.toByteArray(is);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            logger.warn(e.getClass().getName() + " - " + e.getMessage());
            return "";
        }
    }
}
