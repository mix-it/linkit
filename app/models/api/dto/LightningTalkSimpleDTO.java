package models.api.dto;

import java.util.Set;

public class LightningTalkSimpleDTO extends SessionSimpleDTO {

    private long nbVotes;

    public long getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(long nbVotes) {
        this.nbVotes = nbVotes;
    }
}
