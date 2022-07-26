package ru.redcollar.summer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeakerToTalk {

    private Speaker speaker;
    private Long speakerTalks;
}
