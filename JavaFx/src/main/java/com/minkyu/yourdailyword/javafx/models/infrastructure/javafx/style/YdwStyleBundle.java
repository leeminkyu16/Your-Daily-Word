package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style;

import javafx.scene.Node;

import java.util.ArrayList;

public class YdwStyleBundle {
    private final ArrayList<String> styles = new ArrayList<>();
    private final ArrayList<String> styleClasses = new ArrayList<>();

    public YdwStyleBundle setFontSize(int fontSize) {
        return this.addStyleAndReturnThis("-fx-font-size: " + fontSize + ";");
	}

    public YdwStyleBundle setFontWeight(String fontWeight) {
        return this.addStyleAndReturnThis("-fx-font-weight: " + fontWeight + ";");
    }

    public YdwStyleBundle setSpacing(int spacing) {
        return this.addStyleAndReturnThis("-fx-spacing: " + spacing + ";");
    }

    public YdwStyleBundle setAlignment(String alignment) {
        return this.addStyleAndReturnThis("-fx-alignment: " + alignment + ";");
    }

    public YdwStyleBundle setTextAlignment(String textAlignment) {
        return this.addStyleAndReturnThis("-fx-text-alignment: " + textAlignment + ";");
    }

    public YdwStyleBundle setFocusColor(String focusColor) {
        return this.addStyleAndReturnThis("-fx-focus-color: " + focusColor + ";");
    }

    public YdwStyleBundle setFaintFocusColor(String faintFocusColor) {
        return this.addStyleAndReturnThis("-fx-faint-focus-color: " + faintFocusColor + ";");
    }

    public YdwStyleBundle setBackgroundInsets(int top, int right, int bottom, int left) {
        return this.addStyleAndReturnThis("-fx-background-insets: " + top + " " + right + " " + bottom + " " + left + ";");
    }

    public YdwStyleBundle setVGap(int vGap) {
        return this.addStyleAndReturnThis("-fx-vgap: " + vGap + ";");
    }

    public YdwStyleBundle setHGap(int hGap) {
        return this.addStyleAndReturnThis("-fx-hgap: " + hGap + ";");
    }

    public YdwStyleBundle setPadding(int top, int right, int bottom, int left) {
        return this.addStyleAndReturnThis(
        "-fx-padding: " +
            top +
            ", " +
            right +
            ", " +
            bottom +
            ", " +
            left +
            ";"
        );
    }

    public YdwStyleBundle setTextBoxBorder(String color, int width) {
        return this.addStyleAndReturnThis("-fx-text-box-border: " + color + " " + width + "px;");
    }

    public YdwStyleBundle setHighlightFill(String fillColor) {
        return this.addStyleAndReturnThis("-fx-highlight-fill: " + fillColor + ";");
    }


    public YdwStyleBundle setDropShadow(String blurType, String color, float radius, float spread, float xOffset, float yOffset) {
        return this.addStyleAndReturnThis(
            "-fx-effect: dropshadow(" +
                blurType +
                ", " +
                color +
                ", " +
                radius +
                ", " +
                spread +
                ", " +
                xOffset +
                ", " +
                yOffset +
                ");"
        );
    }

    public YdwStyleBundle setBackgroundColor(String backgroundColor) {
        return this.addStyleAndReturnThis(
            "-fx-background-color: " +
                backgroundColor +
                ";"
        );
    }

    public YdwStyleBundle setTextFill(String textFillColor) {
        return this.addStyleAndReturnThis(
            "-fx-text-fill: " + textFillColor + ";"
        );
    }

    public YdwStyleBundle setBorderColor(String borderColor) {
        return this.addStyleAndReturnThis(
            "-fx-border-color: " +
                borderColor +
                ";"
        );
    }

    public YdwStyleBundle setBorderRadius(float borderRadius) {
        return this.addStyleAndReturnThis(
            "-fx-border-radius: " + borderRadius + ";"
        );
    }

    public YdwStyleBundle setBorderStyle(String borderStyle) {
        return this.addStyleAndReturnThis(
            "-fx-border-style: " + borderStyle + ";"
        );
    }

    public YdwStyleBundle setBackgroundRadius(float backgroundRadius) {
        return this.addStyleAndReturnThis(
            "-fx-background-radius: " + backgroundRadius + ";"
        );
    }

    public YdwStyleBundle addStyleAndReturnThis(String styleString) {
        this.styles.add(styleString);
        return this;
    }

    public YdwStyleBundle addStyleClassAndReturnThis(String styleClass) {
        this.styleClasses.add(styleClass);
        return this;
    }

    public String buildStyleString() {
        return String.join("", styles);
    }

    public void apply(Node node) {
        node.setStyle(String.join("", styles));
        node.getStyleClass().addAll(this.styleClasses);
    }

    public static class StyleClasses {
        public static final String PRIMARY_TEXT_AREA = "primary-text-area";
        public static final String ROUNDED_TEXT_BOX = "rounded-text-box";
        public static final String STANDARD_BORDER_PANE = "standard-border-pane";
        public static final String PRIMARY_BUTTON = "primary-button";
        public static final String SECONDARY_BUTTON = "secondary-button";
        public static final String PRIMARY_CHECKBOX = "primary-checkbox";
        public static final String STANDARD_GRID_PANE = "standard-grid-pane";
    }

    public static class Constants {
        public static String FONT_WEIGHT_NORMAL = "normal";
        public static final String FONT_WEIGHT_BOLD = "bold";
        public static String FONT_WEIGHT_BOLDER = "bolder";
        public static String FONT_WEIGHT_LIGHTER = "lighter";
        public static String FONT_WEIGHT_100 = "100";
        public static String FONT_WEIGHT_200 = "200";
        public static String FONT_WEIGHT_300 = "300";
        public static String FONT_WEIGHT_400 = "400";
        public static String FONT_WEIGHT_500 = "500";
        public static String FONT_WEIGHT_600 = "600";
        public static String FONT_WEIGHT_700 = "700";
        public static String FONT_WEIGHT_800 = "800";
        public static String FONT_WEIGHT_900 = "900";

        public static String ALIGNMENT_TOP_LEFT = "top-left";
        public static String ALIGNMENT_TOP_CENTER = "top-center";
        public static String ALIGNMENT_TOP_RIGHT = "top-right";
        public static final String ALIGNMENT_CENTER_LEFT = "center-left";
        public static String ALIGNMENT_CENTER_RIGHT = "center-right";
        public static String ALIGNMENT_BOTTOM_LEFT = "bottom-left";
        public static String ALIGNMENT_BOTTOM_CENTER = "bottom-center";
        public static String ALIGNMENT_BOTTOM_RIGHT = "bottom-right";
        public static String ALIGNMENT_BASELINE_LEFT = "baseline-left";
        public static String ALIGNMENT_BASELINE_CENTER = "baseline-center";
        public static String ALIGNMENT_BASELINE_RIGHT  = "baseline-right ";
        public static final String ALIGNMENT_CENTER = "center";

        public static String TEXT_ALIGNMENT_CENTER = "center";
        public static String TEXT_ALIGNMENT_LEFT = "left";
        public static String TEXT_ALIGNMENT_RIGHT = "right";
        public static String TEXT_ALIGNMENT_JUSTIFY = "justify";

        public static String BORDER_STYLE_NONE = "none";
        public static String BORDER_STYLE_SOLID = "solid";
        public static String BORDER_STYLE_DOTTED = "dotted";
        public static String BORDER_STYLE_DASHED = "dashed";

        public static String BLUR_TYPE_GAUSSIAN = "gaussian";
        public static String BLUR_TYPE_ONE_PASS_BOX = "one-pass-box";
        public static String BLUR_TYPE_THREE_PASS_BOX = "three-pass-box";
        public static String BLUR_TYPE_TWO_PASS_BOX  = "two-pass-box ";
    }
}
