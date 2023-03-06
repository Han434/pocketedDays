package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Row.
 */
@Entity(name = "Row")
@Table(name = "rowOfSheet")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int rowId;
    private int sheetId;
    private int rowCreatorId;
    private LocalDate createdDate;
    private String rowDescription;
    private int quantity;
    private int costPerItem;
    private String rowType;
    private String tag;

    /**
     * Instantiates a new Row.
     */
    public Row() {
    }

    /**
     * Instantiates a new Row.
     *
     * @param sheetId        the sheet id
     * @param rowCreatorId   the row creator id
     * @param createdDate    the created date
     * @param rowDescription the row description
     * @param quantity       the quantity
     * @param costPerItem    the cost per item
     * @param rowType        the row type
     * @param tag            the tag
     */
    public Row(int sheetId, int rowCreatorId, LocalDate createdDate, String rowDescription, int quantity, int costPerItem, String rowType, String tag) {
        this.sheetId = sheetId;
        this.rowCreatorId = rowCreatorId;
        this.createdDate = createdDate;
        this.rowDescription = rowDescription;
        this.quantity = quantity;
        this.costPerItem = costPerItem;
        this.rowType = rowType;
        this.tag = tag;
    }

    /**
     * Gets row id.
     *
     * @return the row id
     */
    public int getRowId() {
        return rowId;
    }

    /**
     * Sets row id.
     *
     * @param rowId the row id
     */
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    /**
     * Gets sheet id.
     *
     * @return the sheet id
     */
    public int getSheetId() {
        return sheetId;
    }

    /**
     * Sets sheet id.
     *
     * @param sheetId the sheet id
     */
    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    /**
     * Gets row creator id.
     *
     * @return the row creator id
     */
    public int getRowCreatorId() {
        return rowCreatorId;
    }

    /**
     * Sets row creator id.
     *
     * @param rowCreatorId the row creator id
     */
    public void setRowCreatorId(int rowCreatorId) {
        this.rowCreatorId = rowCreatorId;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets row description.
     *
     * @return the row description
     */
    public String getRowDescription() {
        return rowDescription;
    }

    /**
     * Sets row description.
     *
     * @param rowDescription the row description
     */
    public void setRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets cost per item.
     *
     * @return the cost per item
     */
    public int getCostPerItem() {
        return costPerItem;
    }

    /**
     * Sets cost per item.
     *
     * @param costPerItem the cost per item
     */
    public void setCostPerItem(int costPerItem) {
        this.costPerItem = costPerItem;
    }

    /**
     * Gets row type.
     *
     * @return the row type
     */
    public String getRowType() {
        return rowType;
    }

    /**
     * Sets row type.
     *
     * @param rowType the row type
     */
    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}