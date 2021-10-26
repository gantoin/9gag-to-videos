package fr.gantoin;

import java.io.IOException;
import java.util.Set;

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

    @GetMapping("hello")
    public String hello() {
        Set<Video> videos = rssService.readRss();
        videos.forEach(video -> {
            try {
                video.setLocalPath(downloadService.download(video.getUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "We have " + videos.size() + " videos";
    }

}
