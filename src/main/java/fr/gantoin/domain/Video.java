package fr.gantoin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Video {

    private String title;

    private String url;

    private String postUrl;

    private String localPath;

}
