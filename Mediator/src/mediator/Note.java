package mediator;

public class Note {
    private String name;
    private String text;

    public Note() {
    	name = "New note";
	}

    public void setName(String name) {
        this.name = name;
    }
    
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}
