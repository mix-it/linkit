package models.api.dto;

import models.TalkLanguage;

public class SpeakerSessionDTO {
    private Long id;
    private String title;
    private String summary;
    private String description;
    private TalkLanguage language;

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
}
