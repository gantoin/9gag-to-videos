package fr.gantoin;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import fr.gantoin.domain.Video;
import fr.gantoin.service.DownloadService;
import fr.gantoin.service.RssService;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ApplicationController {

    private final RssService rssService;

    private final DownloadService downloadService;

    @GetMapping("start")
    public String process() {
        CompletableFuture.runAsync(() -> {

            log.info("Reading RSS feed");
            Set<Video> videos = rssService.readRss();

            if (videos.isEmpty()) {
                log.info("RSS feed is empty, nothing to upload");
            } else {

                log.info("Trying to download {} videos", videos.size());
                videos.forEach(video -> {
                    try {
                        video.setLocalPath(downloadService.download(video.getUrl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                log.info("Videos are successfully downloaded");

                log.info("Trying to upload these {} videos on Youtube", videos.size());
                // TODO
                log.info("Videos are successfully uploaded");

                log.info("Removing videos from tmp file");
                // TODO

            }
        });
        return "Application is running, please check the logs for more information";
    }

}
