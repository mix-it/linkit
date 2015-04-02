package models.api.dto;

import models.TalkFormat;
import models.TalkLanguage;
import models.TalkLevel;
import models.Track;

public class AbstractSessionDTO {

    private Long id;
    private String title;
    private String summary;
    private String description;
    private TalkLanguage language;
    private String ideaForNow;

    /** Only for {@link models.Talk} **/
    private TalkFormat format;
    /** Only for {@link models.Talk} **/
    private TalkLevel level;
    /** Only for {@link models.Talk} **/
    private Track track;

    private String start;   // String to avoid default obscure DateTime serialization
    private String end;
    private String room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TalkLanguage getLanguage() {
        return language;
    }

    public void setLanguage(TalkLanguage language) {
        this.language = language;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public TalkFormat getFormat() {
        return format;
    }

    public void setFormat(TalkFormat format) {
        this.format = format;
    }

    public TalkLevel getLevel() {
        return level;
    }

    public void setLevel(TalkLevel level) {
        this.level = level;
    }

    public String getIdeaForNow() {
        return ideaForNow;
    }

    public void setIdeaForNow(String ideaForNow) {
        this.ideaForNow = ideaForNow;
    }
}
