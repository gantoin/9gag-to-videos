package fr.gantoin.domain.feed;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Feed {
    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    final List<FeedMessage> messages = new ArrayList<>();
}
