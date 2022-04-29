package com.example.aplikacjabankowajava;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class transaction implements Serializable {
    String number;
    Float amount;
    String title;
    String comment;
    String date;
    Long firstAcc;
    String firstAccName;
    Long secondAcc;
    String secondAccName;

    transaction(Float amount,String title, Long senderAcc, String senderName,Long receiverAcc,String receiverName, String comment)
    {
        this(amount,title,senderAcc,senderName,receiverAcc,receiverName);
        this.comment=comment;
    }
    transaction(Float amount,String title, Long senderAcc, String senderName,Long receiverAcc, String receiverName)
    {
        this.date = generateDate();
        this.number = generateTransaction();
        this.title = title;
        this.amount=amount;
        this.firstAcc = senderAcc;
        this.firstAccName = senderName;
        this.secondAcc = receiverAcc;
        this.secondAccName = receiverName;
        this.comment = "";
    }
    private String generateDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = new Date(System.currentTimeMillis());
        return formatter.format(temp);
    }
    private String generateTransaction()
    {
        Random rand = new Random();
        int temp = 1000+rand.nextInt(8999);
        StringBuilder stringBuilder = new StringBuilder().append(this.date.substring(0,3)).append(temp).append(this.date.substring(5,6)+this.date.substring(8,9));
        return stringBuilder.toString();
    }
    public Float getBalance() {
        return amount;
    }

    public void setBalance(Float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getFirstAcc() {
        return firstAcc;
    }

    public void setFirstAcc(Long firstAcc) {
        this.firstAcc = firstAcc;
    }

    public String getFirstAccName() {
        return firstAccName;
    }

    public void setFirstAccName(String firstAccName) {
        this.firstAccName = firstAccName;
    }

    public Long getSecondAcc() {
        return secondAcc;
    }

    public void setSecondAcc(Long secondAcc) {
        this.secondAcc = secondAcc;
    }

    public String getSecondAccName() {
        return secondAccName;
    }

    public void setSecondAccName(String secondAccName) {
        this.secondAccName = secondAccName;
    }
}
