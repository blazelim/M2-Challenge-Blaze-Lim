package com.company.M2ChallengeLimBlaze.models;

import java.util.Objects;

public class Month {
    private Integer number;
    private String name;



    public Month(Integer number) {
        this.number = number;

        switch(number) {
            case 1:  this.name = "January";
                break;
            case 2:  this.name = "February";
                break;
            case 3:  this.name = "March";
                break;
            case 4:  this.name = "April";
                break;
            case 5:  this.name = "May";
                break;
            case 6:  this.name = "June";
                break;
            case 7:  this.name = "July";
                break;
            case 8:  this.name = "August";
                break;
            case 9:  this.name = "September";
                break;
            case 10: this.name = "October";
                break;
            case 11: this.name = "November";
                break;
            case 12: this.name = "December";
                break;
            default: this.name = "Invalid month";
                break;
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return Objects.equals(number, month.number) && Objects.equals(name, month.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }


}
