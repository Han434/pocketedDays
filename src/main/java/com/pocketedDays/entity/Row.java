package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "sheetId",
            foreignKey = @ForeignKey(name = "rowofsheet_sheet_fk")
    )
    private Sheet sheet;
    private int rowCreatorId;
    private LocalDate updatedDate;
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
     * @param sheet          the sheet id
     * @param rowCreatorId   the row creator id
     * @param updatedDate    the created date
     * @param rowDescription the row description
     * @param quantity       the quantity
     * @param costPerItem    the cost per item
     * @param rowType        the row type
     * @param tag            the tag
     */
    public Row(Sheet sheet, int rowCreatorId, LocalDate updatedDate, String rowDescription, int quantity, int costPerItem, String rowType, String tag) {
        this.sheet = sheet;
        this.rowCreatorId = rowCreatorId;
        this.updatedDate = updatedDate;
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
     * Gets sheet.
     *
     * @return the sheet
     */
    public Sheet getSheet() {
        return sheet;
    }

    /**
     * Sets sheet.
     *
     * @param sheet the sheet
     */
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
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
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets created date.
     *
     * @param updatedDate the created date
     */
    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return getRowId() == row.getRowId() && getRowCreatorId() == row.getRowCreatorId() && getQuantity() == row.getQuantity() && getCostPerItem() == row.getCostPerItem() && getSheet().equals(row.getSheet()) && getUpdatedDate().equals(row.getUpdatedDate()) && getRowDescription().equals(row.getRowDescription()) && getRowType().equals(row.getRowType()) && getTag().equals(row.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowId(), getSheet(), getRowCreatorId(), getUpdatedDate(), getRowDescription(), getQuantity(), getCostPerItem(), getRowType(), getTag());
    }

    @Override
    public String toString() {
        return "Row{" +
                "rowId=" + rowId +
                ", sheet=" + sheet +
                ", rowCreatorId=" + rowCreatorId +
                ", updatedDate=" + updatedDate +
                ", rowDescription='" + rowDescription + '\'' +
                ", quantity=" + quantity +
                ", costPerItem=" + costPerItem +
                ", rowType='" + rowType + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}