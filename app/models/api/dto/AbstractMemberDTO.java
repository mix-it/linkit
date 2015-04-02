package models.api.dto;

import java.util.List;

public abstract class AbstractMemberDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String company;
    private String shortdesc;
    private String longdesc;
    private String urlimage;
    private Long nbConsults;

    private List<SharedLinkDTO> sharedLinks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public Long getNbConsults() {
        return nbConsults;
    }

    public void setNbConsults(Long nbConsults) {
        this.nbConsults = nbConsults;
    }

    public List<SharedLinkDTO> getSharedLinks() {
        return sharedLinks;
    }

    public void setSharedLinks(List<SharedLinkDTO> sharedLinks) {
        this.sharedLinks = sharedLinks;
    }
}
