package fr.gantoin.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import fr.gantoin.domain.Video;
import fr.gantoin.domain.feed.Feed;

@Service
@RequiredArgsConstructor
@Slf4j
public class RssService {

    @Value("${application.rssFeed}")
    private String url;

    public Set<Video> readRss() {
        MyRSSFeedParser myRssFeedParser = new MyRSSFeedParser(url);
        Feed feed = myRssFeedParser.readFeed();
        Set<Video> videos = new HashSet<>();
        feed.getMessages().stream() //
                .filter(feedMessage -> feedMessage.getCategory().equals("video")) //
                .filter(feedMessage -> !videos.stream().map(Video::getPostUrl).collect(Collectors.toSet()).contains(feedMessage.getLink())) //
                .forEach(feedMessage -> {
                    Video video = new Video();
                    video.setTitle(feedMessage.getTitle());
                    video.setPostUrl(feedMessage.getLink());
                    video.setUrl(feedMessage.getDescription().substring(feedMessage.getDescription().indexOf("<source src=") + 13, feedMessage.getDescription().indexOf(".mp4") + 4));
                    videos.add(video);
                });
        return videos.stream().filter(n -> !videos.add(n)).collect(Collectors.toSet());
    }
}
