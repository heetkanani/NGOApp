package patwa.aman.com.codeshashtra;

public class VerifiedOrgModel {

    private String description;
    private String email;
    private String mobile;
    private String orgname;
    private String passbook;
    private String proof;
    private String trustno;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public VerifiedOrgModel() {
    }

    public VerifiedOrgModel(String description, String email, String mobile, String orgname, String passbook, String proof, String trustno) {
        this.description = description;
        this.email = email;
        this.mobile = mobile;
        this.orgname = orgname;
        this.passbook = passbook;
        this.proof = proof;
        this.trustno = trustno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPassbook() {
        return passbook;
    }

    public void setPassbook(String passbook) {
        this.passbook = passbook;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getTrustno() {
        return trustno;
    }

    public void setTrustno(String trustno) {
        this.trustno = trustno;
    }
}