package Entity;
import java.io.Serializable;
public class Book implements Serializable{
    private String id;
    private String  name;
    private String author;
    private String publisher;
    private  int store;
    private  int lend;
    private String desc;
    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public int getLend() {
        return lend;
    }

    public void setLend(int lend) {
        this.lend = lend;
    }

}

