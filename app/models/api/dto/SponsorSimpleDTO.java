package models.api.dto;

import models.Sponsor;

public class SponsorSimpleDTO extends MemberSimpleDTO {
    private String logo;
    private Sponsor.Level level;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Sponsor.Level getLevel() {
        return level;
    }

    public void setLevel(Sponsor.Level level) {
        this.level = level;
    }
}
