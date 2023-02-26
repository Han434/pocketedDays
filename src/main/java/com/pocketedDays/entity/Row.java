package com.pocketedDays.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Row")
@Table(name = "income")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "incomeId")
    private int rowId;
    private int sheetId;
    private int rowCreatorId;
    private LocalDate createdDate;
    private String rowDescription;
    private int quantity;
    private int costPerItem;
    @Column(name = "incomeType")
    private String type;
    private String tag;

    public Row() {
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public int getRowCreatorId() {
        return rowCreatorId;
    }

    public void setRowCreatorId(int rowCreatorId) {
        this.rowCreatorId = rowCreatorId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getRowDescription() {
        return rowDescription;
    }

    public void setRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCostPerItem() {
        return costPerItem;
    }

    public void setCostPerItem(int costPerItem) {
        this.costPerItem = costPerItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
