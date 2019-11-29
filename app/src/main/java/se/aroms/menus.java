package se.aroms;

public class menus {
    String uid;
    String name;
    String type;
    String time;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public menus(String menuid, String n) {
        this.uid = menuid;
        this.name = n;
    }
    public menus(String menuid, String n,String t) {
        this.uid = menuid;
        this.name = n;
        this.type=t;
    }



    public menus(String uid, String name, String type, String time) {
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getTime() {
        return time;
    }

    public menus() {
    }

    public String getuid() {
        return uid;
    }

    public void setuid(String menuid) {
        this.uid = menuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
