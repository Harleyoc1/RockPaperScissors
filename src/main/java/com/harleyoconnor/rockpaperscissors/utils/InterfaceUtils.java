package com.harleyoconnor.rockpaperscissors.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * @author Harley O'Connor
 */
public final class InterfaceUtils {

    public static ImageView createImageView (final String imagePath, final int height, final int width) {
        final ImageView imageView = new ImageView(new Image(imagePath));

        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        return imageView;
    }

    public static Region createSpacer (final boolean horizontal) {
        final Region spacer = new Region();
        if (horizontal) HBox.setHgrow(spacer, Priority.ALWAYS);
        else VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static HBox createHBoxWithCentralElements (final int spacing, final Node... nodes) {
        final HBox hBox = new HBox(spacing);

        addSpacerTo(hBox, true);
        addElementsToPane(hBox, nodes);
        addSpacerTo(hBox, true);

        return hBox;
    }

    public static <T extends Pane> T addSpacerTo (final T pane, final boolean horizontal) {
        pane.getChildren().add(createSpacer(horizontal));
        return pane;
    }

    public static <T extends Pane> T addElementsToPane (final T pane, final Node... nodes) {
        pane.getChildren().addAll(nodes);
        return pane;
    }

    public static <T extends Pane> T removeElementsFromPane (final T pane, final Node... nodes) {
        pane.getChildren().removeAll(nodes);
        return pane;
    }

    public static Label createLabel (final String initialText, final Font font) {
        final Label label = createLabel(initialText);
        label.setFont(font);
        return label;
    }

    public static Label createLabel (final String initialText) {
        final Label label = new Label(initialText);
        label.setWrapText(true);
        return label;
    }

    public static <T extends Region> T setSizeOfNode (final T node, final int width, final int height) {
        node.setMinWidth(width);
        node.setMinHeight(height);

        return node;
    }

    public static <T extends Region> T addTopPadding (final T region, final int padding) {
        region.setPadding(new Insets(padding, 0, 0, 0));
        return region;
    }

    public static <T extends Node> T addStyleClassesTo (final T node, final String... styleClasses) {
        node.getStyleClass().addAll(styleClasses);
        return node;
    }

}
