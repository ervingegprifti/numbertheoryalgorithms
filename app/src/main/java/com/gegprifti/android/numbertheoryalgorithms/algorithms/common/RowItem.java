package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


public class RowItem {

    public enum HeaderStyle { DEFAULT, OUTLINED, HIGHLIGHTED, BLANK }
    public enum ValueStyle {
        DEFAULT,
        YELLOW,
        YELLOW_STRESSED,
        GREEN,
        GREEN_STRESSED,
        ORANGE,
        ORANGE_STRESSED,
        BLUE,
        BLUE_STRESSED,
        BLACK,
        WHITE,
        WHITE_STRESSED }
    public enum ValueToDisplay { VALUE_1, VALUE_2 }

    public String getValue() {
        if (this.valueToDisplay == ValueToDisplay.VALUE_1) {
            return this.value1;
        } else if (this.valueToDisplay == ValueToDisplay.VALUE_2) {
            return this.value2;
        } else {
            return this.value1;
        }
    }

    private String value1;
    public String getValue1 () { return this.value1; }
    public void setValue1(String value1) { this.value1 = value1; }

    private String value2;
    public String getValue2 () { return this.value2; }
    public void setValue2(String value2) { this.value2 = value2; }

    private ValueToDisplay valueToDisplay;
    public ValueToDisplay getValueToDisplay() { return this.valueToDisplay; }
    public void setValueToDisplay(ValueToDisplay valueToDisplay) {
        this.valueToDisplay = valueToDisplay;
    }

    private HeaderStyle headerStyle;
    public HeaderStyle getHeaderStyle() { return this.headerStyle; }
    public void setHeaderStyle(HeaderStyle headerStyle) {
        this.headerStyle = headerStyle;
    }

    private ValueStyle valueStyle;
    public ValueStyle getValueStyle() { return this.valueStyle; }
    public void setValueStyle(ValueStyle valueStyle) {
        this.valueStyle = valueStyle;
    }

    private final boolean isHeader;
    public boolean getIsHeader() { return this.isHeader; }

    private final boolean isConfig;
    public boolean getIsConfig() { return this.isConfig; }

    private final boolean isPrime;
    public boolean getIsPrime() { return this.isPrime; }

    private boolean isSelected;
    public boolean getIsSelected() {
        return this.isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private int quadrant = 0;
    /**
     * Get the quadrant.
     * @return The quadrant: 1 (+x,+y), 2 (-x,+y), 3 (-x,-y), 4 (+x,-y).
     */
    public int getQuadrant() {
        return this.quadrant;
    }
    /**
     * Set the quadrant.
     * @param quadrant  The quadrant: 1 (+x,+y), 2 (-x,+y), 3 (-x,-y), 4 (+x,-y).
     */
    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    public RowItem (boolean isHeader, boolean isConfig) {
        this(isHeader, isConfig, "", false, HeaderStyle.DEFAULT, ValueStyle.DEFAULT);
    }
    public RowItem (boolean isHeader, String value1, boolean isPrime) {
        this(isHeader, false, value1, isPrime, HeaderStyle.DEFAULT, ValueStyle.DEFAULT);
    }
    public RowItem (boolean isHeader, String value1, boolean isPrime, HeaderStyle headerStyle) {
        this(isHeader, false, value1, isPrime, headerStyle, ValueStyle.DEFAULT);
    }
    public RowItem (boolean isHeader, String value1, boolean isPrime, ValueStyle valueStyle) {
        this(isHeader, false, value1, isPrime, HeaderStyle.DEFAULT, valueStyle);
    }
    public RowItem(boolean isHeader, boolean isConfig, String value1, boolean isPrime, HeaderStyle headerStyle, ValueStyle valueStyle) {
        this.isHeader = isHeader;
        this.isConfig = isConfig;
        this.value1 = value1;
        this.isPrime = isPrime;
        this.headerStyle = headerStyle;
        this.valueStyle = valueStyle;
        //
        this.valueToDisplay = ValueToDisplay.VALUE_1;
    }

}