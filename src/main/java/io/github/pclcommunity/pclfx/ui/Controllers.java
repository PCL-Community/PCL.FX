package io.github.pclcommunity.pclfx.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import io.github.pclcommunity.pclfx.config.Config;
import io.github.pclcommunity.pclfx.config.ConfigHolder;
import io.github.pclcommunity.pclfx.util.Log;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controllers {
    public static Stage stage;

    public static void initialize(Stage primaryStage) {
        Log.LOGGER.info("Initializing controllers");
        stage = primaryStage;

        Config config = ConfigHolder.config();
        stage.setWidth(config.width);
        stage.setHeight(config.height);

        stage.setMinWidth(810);
        stage.setMinHeight(470);

        addSizeChangeListener();
        stage.setTitle("PCL.FX");

        Frame frame = new Frame(primaryStage);

        StackPane shadowPane = new StackPane();
        shadowPane.getChildren().add(frame);
        StackPane.setMargin(frame, new Insets(10));
        shadowPane.setBackground(Background.EMPTY);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        shadowPane.setEffect(dropShadow);

        BorderlessScene scene = new BorderlessScene(primaryStage, StageStyle.TRANSPARENT, shadowPane, 810, 470);
        scene.setFill(Color.TRANSPARENT);
        scene.removeDefaultCSS();
        primaryStage.setScene(scene);

        Log.LOGGER.info("Controllers initialized successfully");
    }

    private static void addSizeChangeListener() {
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            ConfigHolder.config().width = newValue.intValue();
            ConfigHolder.saveConfig();
            Log.LOGGER.debug("Window width changed to: {}", newValue);
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            ConfigHolder.config().height = newValue.intValue();
            ConfigHolder.saveConfig();
            Log.LOGGER.debug("Window height changed to: {}", newValue);
        });
    }
}
