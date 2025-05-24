package io.github.pclcommunity.pclfx.ui;

import io.github.pclcommunity.pclfx.ui.controls.IconButton;
import io.github.pclcommunity.pclfx.ui.controls.RadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
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

        Label fxLabel = new Label("FX");
        fxLabel.getStyleClass().add("title-bar-fx-label");

        HBox leftContainer = new HBox(0, logo, fxLabel);

        Insets margin = new Insets(14, 0, 0, 25);
        Insets fxMargin = new Insets(10, 0, 0, -35);

        HBox.setMargin(logo, margin);
        HBox.setMargin(fxLabel, fxMargin);

        setLeft(leftContainer);

        HBox nav = createNav();
        nav.setAlignment(Pos.CENTER);
        setCenter(nav);

        HBox rightContainer = new HBox(0);
        rightContainer.getChildren().addAll(createControls());
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        Scale rightScale = new Scale(0.7, 0.7);
        rightContainer.getTransforms().add(rightScale);

        setRight(rightContainer);
    }

    private HBox createNav() {
        HBox nav = new HBox(10);
        nav.setPadding(new Insets(15));
        nav.setAlignment(Pos.CENTER);

        ToggleGroup group = new ToggleGroup();

        RadioButton launchButton = new RadioButton("启动", Icons.POWER);
        RadioButton downloadButton = new RadioButton("下载", Icons.DOWNLOAD);
        RadioButton settingButton = new RadioButton("设置", Icons.SETTING, 0.48);
        RadioButton moreButton = new RadioButton("更多", Icons.MORE);

        launchButton.setToggleGroup(group);
        downloadButton.setToggleGroup(group);
        settingButton.setToggleGroup(group);
        moreButton.setToggleGroup(group);

        group.selectToggle(launchButton);
        nav.getChildren().addAll(launchButton, downloadButton, settingButton, moreButton);
        return nav;
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