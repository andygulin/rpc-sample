package rpc.sample.registry.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import rpc.sample.registry.Registry;
import rpc.sample.service.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileRegistry implements Registry {

    private static final Logger logger = Logger.getLogger(FileRegistry.class);
    private static final String FILE_NAME = UserService.class.getName();
    private static final Path SERVER_URL_PATH = Paths.get(System.getProperty("java.io.tmpdir"), FILE_NAME);

    @Override
    public void register(List<String> urls) {
        try {
            Files.deleteIfExists(SERVER_URL_PATH);
            Files.write(SERVER_URL_PATH, urls, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("register url : " + urls + " , path : " + SERVER_URL_PATH);
    }

    @Override
    public List<String> getAll() {
        List<String> urls = null;
        try {
            urls = Files.readAllLines(SERVER_URL_PATH, StandardCharsets.UTF_8);
            urls.removeIf(StringUtils::isEmpty);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return urls;
    }
}