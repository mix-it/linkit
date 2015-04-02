package models.api.dto;

public class LightningTalkDetailedDTO extends SessionDetailedDTO {

    private long nbVotes;

    public long getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(long nbVotes) {
        this.nbVotes = nbVotes;
    }
}
