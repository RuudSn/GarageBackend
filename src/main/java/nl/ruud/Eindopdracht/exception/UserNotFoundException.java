package nl.ruud.Eindopdracht.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public UserNotFoundException(String name){
        super(name + " not found");
    }
    public UserNotFoundException() {
        super("User not found.");
    }

}
