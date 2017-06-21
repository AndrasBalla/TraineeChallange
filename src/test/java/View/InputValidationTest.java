package View;

import main.java.Model.Library;
import main.java.View.InputValidation;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputValidationTest{
    private InputValidation sut = new InputValidation();

    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    @Test
    public void testValidateUserInput(){
        String nameMock = "Name";
        String languageMock = "Language";
        assertTrue(sut.validateUserInput(nameMock, languageMock));

        nameMock = "";
        languageMock = "";
        assertFalse(sut.validateUserInput(nameMock, languageMock));

        nameMock = "Name";
        languageMock = "";
        assertFalse(sut.validateUserInput(nameMock, languageMock));

        nameMock = "";
        languageMock = "Language";
        assertFalse(sut.validateUserInput(nameMock, languageMock));
    }

    @Test
    public void testValidateSelectedField(){
        Library libMock = Mockito.mock(Library.class);

        assertTrue(sut.validateSelectedField(libMock));

        assertFalse(sut.validateSelectedField(null));
    }
}
