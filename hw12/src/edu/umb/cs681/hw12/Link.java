package edu.umb.cs681.hw12;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        this.target = target;
    }
    public FSElement getTarget() {
        return this.target;
    }
    public boolean isDirectory() {
        return false;
    }
    public boolean isFile() {
        return false;
    }
    public boolean isLink() {
        return true;
    }
}