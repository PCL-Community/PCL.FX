package io.github.pclcommunity.pclfx.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Frame extends BorderPane {
    private final TitleBar titleBar;

    public Frame(Stage stage) {
        getStylesheets().add("assets/style.css");
        getStyleClass().add("window");

        titleBar = new TitleBar(stage);
        setTop(titleBar);

        HBox center = new HBox();
        center.getStyleClass().add("center");
        setCenter(center);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);
    }

    public TitleBar getTitleBar() {
        return titleBar;
    }
}