package cst438.lookbook;

public class Book {
    private int id;
    private String title;
    private String author;
    private String imagelink;
    private String apiId;

    public Book(int id, String title, String author, String imagelink, String apiId){
        this.id = id;
        this.title = title;
        this.author = author;
        this.imagelink = imagelink;
        this.apiId = apiId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getApiId() { return apiId; }

    public void setApiId(String apiId) { this.apiId = apiId; }

}
