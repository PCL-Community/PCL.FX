package io.github.pclcommunity.pclfx.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import io.github.pclcommunity.pclfx.config.Configs;
import io.github.pclcommunity.pclfx.config.PCLFXConfig;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controllers {
    private static Stage stage;
    private static TitleBar titleBar;
    private static SideBar sideBar;

    public static void initialize(Stage primaryStage) {
        stage = primaryStage;

        PCLFXConfig config = Configs.config();
        stage.setWidth(config.width);
        stage.setHeight(config.height);

        stage.setMinWidth(810);
        stage.setMinHeight(470);

        addSizeChangeListener();

        stage.setTitle("PCL.FX");

        Frame frame = new Frame();

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
    }

    private static void addSizeChangeListener() {
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            Configs.config().width = newValue.intValue();
            Configs.saveConfig();
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            Configs.config().height = newValue.intValue();
            Configs.saveConfig();
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static TitleBar getTitleBar() {
        if (titleBar == null) titleBar = new TitleBar();
        return titleBar;
    }

    public static SideBar getSideBar() {
        if (sideBar == null) sideBar = new SideBar();
        return sideBar;
    }
}
