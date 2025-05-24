package io.github.pclcommunity.pclfx.ui;

import io.github.pclcommunity.pclfx.ui.controls.IconButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {
    private final Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    public TitleBar(Stage stage) {
        this.stage = stage;
        initialize();
        setupDragHandlers();
    }

    private void initialize() {
        getStyleClass().add("title-bar");
        setPadding(new Insets(0));

        setMaxHeight(45);
        setPrefHeight(45);
        setMinHeight(45);

        SVGPath logo = createLogo();

        Scale scale = new Scale(0.6, 0.6);
        logo.getTransforms().add(scale);

        HBox leftContainer = new HBox(0, logo);
        Insets margin = new Insets(14, 0, 0, 25);
        HBox.setMargin(logo, margin);

        setLeft(leftContainer);

        HBox rightContainer = new HBox(0);
        rightContainer.getChildren().addAll(createControls());
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        Scale rightScale = new Scale(0.7, 0.7);
        rightContainer.getTransforms().add(rightScale);

        setRight(rightContainer);
    }

    private SVGPath createLogo() {
        SVGPath logo = new SVGPath();
        logo.setContent(Icons.PCL.pathData);
        logo.setFill(Color.TRANSPARENT);
        logo.setStroke(Color.WHITE);
        logo.setStrokeWidth(3);
        return logo;
    }


    private IconButton[] createControls() {
        IconButton[] buttons = new IconButton[2];
        buttons[0] = new IconButton(Icons.MIN.pathData);
        buttons[1] = new IconButton(Icons.CLOSE.pathData);

        buttons[0].setOnAction(event -> stage.setIconified(true));
        buttons[1].setOnAction(event -> stage.close());

        buttons[0].setPrefWidth(24);
        buttons[1].setPrefWidth(24);


        HBox.setMargin(buttons[0], new Insets(13, 0, 2, 0));
        HBox.setMargin(buttons[1], new Insets(13, 0, 2, 0));

        return buttons;
    }

    private void setupDragHandlers() {
        setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
    }
}