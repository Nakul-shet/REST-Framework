package tests.ReqResExample;

public class ReqResUserPOJO {
    private String name;
    private String job;

    public ReqResUserPOJO() {}

    public ReqResUserPOJO(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
