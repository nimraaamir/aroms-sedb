package se.aroms.Devdroids;

public class items_queue {
    private String ItemID;
    private String size;
    private String expected_time;//actual time for cooking
    private String required_time;// time required to start cooking in queue time
    private int status;
    public items_queue() {
    }

    public items_queue(String itemID, String size, String expected_time, String required_time,int status) {
        ItemID = itemID;
        this.size = size;
        this.expected_time = expected_time;
        this.required_time = required_time;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExpected_time() {
        return expected_time;
    }

    public void setExpected_time(String expected_time) {
        this.expected_time = expected_time;
    }

    public String getRequired_time() {
        return required_time;
    }

    public void setRequired_time(String required_time) {
        this.required_time = required_time;
    }
}
