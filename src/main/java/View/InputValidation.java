package main.java.View;

import main.java.Model.Library;

public class InputValidation {
    /**
     * Validate the input fields to make sure that they all input values. Also provideds error messages to the user.
     * @param name, The TextField that receives the name of the library.
     * @param language A TextField that receives the programming language.
     * @return Boolean value. True if all input fields are filled out else false.
     */
    public boolean validateUserInput(String name, String language){
        return !(name.equals("") || language.equals(""));
    }

    /**
     * Validate that the user has selected a object in the list.
     * @param selected, Library object selected by the user.
     * @return True if a object is selected else false.
     */
    public boolean validateSelectedField(Library selected) {
        return !(selected == null);
    }
}
