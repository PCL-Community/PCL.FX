package io.github.pclcommunity.pclfx;

import io.github.pclcommunity.pclfx.ui.Controllers;
import io.github.pclcommunity.pclfx.util.Log;
import javafx.application.Application;
import javafx.stage.Stage;

public class PCLFxApplication extends Application {
    public static void main(String[] args) {
        Log.LOGGER.info("Starting application");
        launch(args);
    }

    public void start(Stage stage) {
        Log.LOGGER.debug("Initializing primary stage");
        Controllers.initialize(stage);
        stage.show();
    }
}
