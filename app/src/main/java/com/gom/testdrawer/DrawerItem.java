package com.gom.testdrawer;

public class DrawerItem {

    String title, date, content, id;

    public DrawerItem( String content , String date, String title, String id){
        this.title = title;
        this.date = date;
        this.content = content;
        this.id = id;


    }

    public DrawerItem(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
