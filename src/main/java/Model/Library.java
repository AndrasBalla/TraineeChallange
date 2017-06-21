package main.java.Model;


import javafx.beans.property.SimpleStringProperty;

public class Library {
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleStringProperty language;
    private final SimpleStringProperty intrusive;
    private final SimpleStringProperty openSource;

    public Library(String name, String type, String language, String intrusive, String openSource){
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.language = new SimpleStringProperty(language);
        this.intrusive = new SimpleStringProperty(intrusive);
        this.openSource = new SimpleStringProperty(openSource);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getLanguage() {
        return language.get();
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getIntrusive() {
        return intrusive.get();
    }

    public void setIntrusive(String intrusive) {
        this.intrusive.set(intrusive);
    }

    public String getOpenSource() {
        return openSource.get();
    }

    public void setOpenSource(String openSource) {
        this.openSource.set(openSource);
    }

    /**
     * Overring this method since it used by the contains() method.
     * @param object
     * @return Boolean Value. True if the two objects are the same else false.
     */
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Library)
        {
            sameSame = this.name.toString().equals(((Library) object).name.toString());
        }

        return sameSame;
    }
}
