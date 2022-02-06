package fr.gantoin.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DownloadService {

    public String download(String title, String address) throws IOException {
        String out = "/tmp/" + title + ".mp4";
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(address).openStream());
             FileOutputStream fileOS = new FileOutputStream(out)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            // handles IO exceptions
        }
        log.info("Downloaded video file: " + out);
        return out;
    }

}
