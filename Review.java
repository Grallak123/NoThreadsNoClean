/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author frith
 */
public class Review {
    
    
    String reviewCreatorID;
    String entryDateTime;
    String reviewText;
    String reviewCreatorUName;
    
 
    
    
    
    public Review(String reviewCreatorID,String inputDateTime,String reviewText,String reviewCreatorUName){
        this.reviewCreatorID = reviewCreatorID;
        //LocalDateTime myDateObj = LocalDateTime.now();
        //DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
       // this.entryDateTime = myDateObj.format(myFormatObj);
        this.entryDateTime = inputDateTime;
        this.reviewText = reviewText;
        this.reviewCreatorUName = reviewCreatorUName;
    }
    
    public String getReviewCreatorID(){
        return this.reviewCreatorID;
    }
    
    public String getEntryDateTime(){
        return this.entryDateTime;
    }
    
    public String getReviewText(){
        return this.reviewText;
    }
    
    public String getReviewCreatorUName(){
        return this.reviewCreatorUName;
    }
    
    public void setReviewCreatoriD(String newReviewCreatorID){
       this.reviewCreatorID = newReviewCreatorID;
    }
   
    public void setEntryDateTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.entryDateTime = myDateObj.format(myFormatObj);
    }
   
    public void setReviewText(String newReviewText){
       this.reviewText = newReviewText;
    }
   
    public void setReviewCreatorUName(String newReviewCreatorUName){
       this.reviewCreatorUName = newReviewCreatorUName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review other = (Review) o;
        return reviewCreatorID.equals(other.reviewCreatorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewCreatorUName);
    }
    
            
    @Override
    public String toString(){
                 
        String info = "Reviewer Name: " + this.reviewCreatorUName + "\n";
        
    
        return info;
    }
    
    public String toString2(){
                 
        String info = "Reviewer Name: " + this.reviewCreatorUName + ". Entry date&time " +
                entryDateTime + ". reviewText: " + reviewText + ". Reviewer ID: "
                + this.reviewCreatorID + "\n";
        
    
        return info;
    }
    
    
}
