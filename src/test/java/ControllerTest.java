import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.FlowView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Controller;
import org.testfx.framework.junit.ApplicationTest;

public class ControllerTest extends ApplicationTest{
    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        Flow flow = new Flow(Controller.class);
        FlowHandler handler = flow.createHandler();
        stage.setScene(new Scene(handler.start()));
        stage.show();
        FlowView view = handler.getCurrentView();
        controller = (Controller) view.getViewContext().getController();
    }

    /*Doesnt work right now*/
    /*@Test
    public void test() throws Exception {
        interact(() -> {
            controller.add();
        });
    }*/
}
