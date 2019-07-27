package club.plus1.forcetaxi.model;

import java.util.Date;

public class User {
    private int ok;
    private Error error;
    private String name;
    private String inn;
    private Date date;
    private String businessType;
    private boolean isTighten;
    private boolean isInFns;
    private boolean isForceAccepted;
    private boolean isPinSet;
    private String inFnsUrl;
    private String forceAcceptUrl;
    private String shareUrl;

    public User() {
        this.ok = 0;
        this.error = null;
        this.name = "";
        this.inn = "";
        this.date = new Date();
        this.businessType = "";
        this.isTighten = false;
        this.isInFns = false;
        this.isForceAccepted = false;
        this.isPinSet = false;
        this.inFnsUrl = "";
        this.forceAcceptUrl = "";
        this.shareUrl = "";
    }
}
