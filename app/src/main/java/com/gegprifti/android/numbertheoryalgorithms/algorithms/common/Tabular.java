package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import java.util.ArrayList;
import java.util.List;


public class Tabular {
    public enum Align { LEFT, RIGHT }

    private List<List<String>> rows = new ArrayList<>();
    private Align align = Align.LEFT;

    public Tabular(){

    }

    public void setAlign (Align align) {
        this.align = align;
    }

    public void addRow(List<String> row) {
        this.rows.add(row);
    }

    public String render() throws Exception {
        if (this.rows.size() <= 0) {
            throw new Exception("Nothing to render. Rows are empty.");
        }
        StringBuilder rowsBuilder = new StringBuilder();
        List<Integer> maxColumnSizes = getMaxColumnSizes();
        for (int r = 0; r < rows.size(); r++) {
            StringBuilder rowBuilder = new StringBuilder();
            List<String> row = rows.get(r);
            for (int i = 0; i < row.size(); i++) {
                String item = row.get(i);
                int maxColumnSize = maxColumnSizes.get(i);
                if (this.align == Align.LEFT) {
                    rowBuilder.append(padSpacesRight(item, maxColumnSize));
                } else if (this.align == Align.RIGHT) {
                    rowBuilder.append(padSpacesLeft(item, maxColumnSize));
                } else {
                    rowBuilder.append(item);
                }
            }
            rowsBuilder.append(rowBuilder).append("<br>");
        }
        return rowsBuilder.toString();
    }

    private String generateCharacters(String character, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            sb.append(character);
        }
        return sb.toString();
    }

    private List<Integer> getMaxColumnSizes() {
        List<Integer> maxColumnSizes = new ArrayList<>();
        for (int r = 0; r < rows.size(); r++) {
            List<String> row = rows.get(r);
            for (int i = 0; i < row.size(); i++) {
                if (i < maxColumnSizes.size()) {
                    if (row.get(i).length() > maxColumnSizes.get(i)) {
                        maxColumnSizes.set(i, row.get(i).length());
                    }
                } else {
                    maxColumnSizes.add(row.get(i).length());
                }
            }
        }
        return maxColumnSizes;
    }

    private String padSpacesLeft (String value, int length) {
        if (length <= value.length()) {
            return value;
        }
        //String nbs = "\u00A0"; // non-breaking space
        //String nbs = " ";
        String nbs = "&nbsp;";
        String fillCharacters = generateCharacters(nbs, length - value.length());
        return fillCharacters + value;
    }
    private String padSpacesRight (String value, int length) {
        if (length <= value.length()) {
            return value;
        }
        //String nbs = "\u00A0"; // non-breaking space
        //String nbs = " ";
        String nbs = "&nbsp;";
        String fillCharacters = generateCharacters(nbs, length - value.length());
        return value + fillCharacters;
    }

}