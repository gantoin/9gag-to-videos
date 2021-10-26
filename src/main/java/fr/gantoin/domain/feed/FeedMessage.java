package fr.gantoin.domain.feed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class FeedMessage {
    String title;
    String description;
    String link;
    String guid;
    String pubDate;
    String category;
}
