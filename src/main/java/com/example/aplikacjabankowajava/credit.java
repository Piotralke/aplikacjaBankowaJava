package com.example.aplikacjabankowajava;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class credit implements Serializable{
    private String purpose;
    private Float amount;
    private int period;
    private int day;
    private Float earnings;
    private int people;
    private Float expenses;
    private String date;
    private String number;
    private Float instalment;
    private int age;
    private Long numAcc;
    private String status;
    private String currency;
    private String name;
    private String surname;
    private Float creditSum;
    private int remainingInstalments;
    private LocalDate nextPayment;


    public credit(String purpose, Float amount, int period, int day, Float earnings, int people, Float expenses, int age, Long numAcc, String currency, String name, String surname) {
        this.purpose = purpose;
        this.amount = amount;
        this.period = period;
        this.day = day;
        this.earnings = earnings;
        this.people = people;
        this.expenses = expenses;
        this.date = generateDate();
        this.number = generateCredit();
        this.instalment = (amount*1.069f)/period;
        this.age = age;
        this.numAcc = numAcc;
        this.status = "Oczekujący";
        this.currency = currency;
        this.name = name;
        this.surname = surname;
        this.creditSum = this.instalment*this.period;
        this.remainingInstalments = this.period;
    }

    private String generateDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = new Date(System.currentTimeMillis());
        return formatter.format(temp);
    }

    private String generateCredit(){
        Random rand = new Random();
        int temp = 10000+rand.nextInt(89999);
        StringBuilder stringBuilder = new StringBuilder().append("CR-").append(this.date.substring(0,4)).append(temp).append(this.date.substring(5,7)+this.date.substring(8,10));
        return stringBuilder.toString();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Float getEarnings() {
        return earnings;
    }

    public void setEarnings(Float earnings) {
        this.earnings = earnings;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public Float getExpenses() {
        return expenses;
    }

    public void setExpenses(Float expenses) {
        this.expenses = expenses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Float getInstalment() {
        return instalment;
    }

    public void setInstalment(Float instalment) {
        this.instalment = instalment;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getNumAcc() {
        return numAcc;
    }

    public void setNumAcc(Long numAcc) {
        this.numAcc = numAcc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Float getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(Float creditSum) {
        this.creditSum = creditSum;
    }

    public int getRemainingInstalments() {
        return remainingInstalments;
    }

    public void setRemainingInstalments(int remainingInstalments) {
        this.remainingInstalments = remainingInstalments;
    }

    public LocalDate getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(LocalDate nextPayment) {
        this.nextPayment = nextPayment;
    }
}
