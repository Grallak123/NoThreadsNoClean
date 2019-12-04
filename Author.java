/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author frith
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  The Class of Author that implements Serializable and has author name and
 * localdatestamp.
 * @author frith
 *
*  */
public class Author{
    
    private String name;
    private String authorCreator;
    private String authorID;
    
    /**The Constructor of Author that takes the input of name and creates a 
     *date stamp for date when the author was added
     * @param name String the name of the author you want to create.
     * @param authorCreator
     * @param authorID
     */
    public Author(String name,String authorCreator,String authorID){
        this.name = name;
        this.authorCreator = authorCreator;
        this.authorID = authorID;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public void setAuthorCreator(String newAuthorCreator){
        this.authorCreator = newAuthorCreator;
    }  
    
    public void setAuthorID(String newAuthorID){
        this.authorID = newAuthorID;
    }
    
    
    /**Accessor method that returns the name of an author
     * @return name Name the name of this author object.
     */
    public String getName(){
         
        return this.name;
    }
    public String getAuthorCreator(){ return this.authorCreator; }
    
    public String getAuthorID(){
         
        return this.authorID;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author other = (Author) o;
        return name.equals(other.name) && authorID.equals(other.authorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,authorID);
    }    
    
    
    /** toString method of the Author with name and local date
     * @return info String returns the string info of this object
     *              inludes name and localdate.
     */
    @Override
    public String toString(){
                 
        String info = "Author name: "+this.name +"\n  ";
        
    
        return info;
    }
    
    //@Override
    public String toString2(){
                 
        String info = "Author ID: " +this.authorID +" Author name: "+name + ". creator " +authorCreator + "\n";
        
    
        return info;
    }    
    
    
    
}