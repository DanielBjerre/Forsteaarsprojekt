package presentation;
import database.ReadUser;
import entities.Employee;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setMaximized(true);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		SceneHovedmenu scHM = new SceneHovedmenu();
		scHM.init(primaryStage);
		ConstantsPresentation.stage = primaryStage;
		ReadUser ru = new ReadUser();
		Employee test = ru.login("Test", "Test");
		System.out.println(test.getFirstName());
	}
}