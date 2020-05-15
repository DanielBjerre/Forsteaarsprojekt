package presentation;
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
		SceneLogin sl = new SceneLogin();
		sl.init(primaryStage);
	}
}