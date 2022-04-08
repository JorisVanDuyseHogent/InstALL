package com.jorisduyse.install.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ResourceController {
    private static Properties langPack;
    private static Properties settings;
    private static Properties properties;

    private static boolean langBool;

    public static void setSettings(String settingsNaam) {
        Properties fallback = new Properties();
        fallback.put("key", "default");
        settings = new Properties(fallback);

        URL res = ResourceController.class.getResource(settingsNaam);
        System.err.println(res);
        if (res == null) throw new UncheckedIOException(new FileNotFoundException(settingsNaam));
        URI uri;
        try { uri = res.toURI(); }
        catch (URISyntaxException ex) { throw new IllegalArgumentException(ex); }

        try (InputStream is = Files.newInputStream(Paths.get(uri))) { settings.load(is); }
        catch (IOException ex) { throw new UncheckedIOException("Failed to load resource", ex); }
        System.err.println("Language= " + settings.getProperty("Language")
                + "\nTheme= " + settings.getProperty("Theme")
                + "\nResolutionX= " + settings.getProperty("ResolutionX")
                + " ResolutionY= " + settings.getProperty("ResolutionY")
        );

    }

    public static void setLangPack(String LangPackNaam) {
        Properties fallback = new Properties();
        fallback.put("key", "default");
        langPack = new Properties(fallback);

        URL res = ResourceController.class.getResource(LangPackNaam);
        if (res == null) throw new UncheckedIOException(new FileNotFoundException(LangPackNaam));
        URI uri;
        try { uri = res.toURI(); }
        catch (URISyntaxException ex) { throw new IllegalArgumentException(ex); }

        try (InputStream is = Files.newInputStream(Paths.get(uri))) { langPack.load(is); }
        catch (IOException ex) { throw new UncheckedIOException("Failed to load resource", ex); }

    }
    public static String getSetting(String key) {
        return settings.getProperty(key);
    }

    public static String getTranslation(String key) {
        return langPack.getProperty(key);
    }
}
