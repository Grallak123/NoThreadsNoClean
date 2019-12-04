/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Author;
import model.Book;
import model.Library2Model;
import model.Review;

public class Library2Controller {
    
    private final Library2Model model;
    private final Library2View view;

    public Library2Controller(Library2Model model, Library2View view) {
        this.model = model;
        this.view = view;
    }
    
    public void handleCheckLogin(String username,String password){
        System.out.println(username + " " + password);
        System.out.println("ree");
        try{
            model.tryToConnect(username,password);
            //view.mainMenuScene();
            model.setPassword(password);
            model.setUsername(username);
            view.bigTableBookScene();
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        } catch(Exception er){
            System.out.println(er.getMessage());
        }

        
    }
    
    
    

   
   public void handleCustomQuery(String chosenGenre,String chosenISBN,
           String chosenTitle,String chosenPublisher,String chosenAuthor,String chosenRating){
                 //"SELECT * FROM Book"  
        ArrayList<Book> aBookList = new ArrayList<Book>();
        String customQuery = "";
        
        customQuery += "Select book.*,authorname,authorcreator,author.authorid from book";

        if("Any".matches(chosenGenre.toString())){  
            customQuery += " join authorof on authorof.isbn = book.isbn";
            customQuery += " join author on author.authorid = authorof.authorid";
            customQuery += " where book.isbn like '%" + chosenISBN + "%'";
            customQuery += " and book.title like '%" + chosenTitle + "%'";
            customQuery += " and book.publisher like '%" + chosenPublisher + "%'";
            customQuery += " and book.rating like '%" + chosenRating + "%'";
            customQuery += " and author.authorname like '%" + chosenAuthor + "%'";
           // customQuery += " order by author.authorID ASC";
            customQuery += " order by isbn DESC";
            
        }else{ 
            customQuery += " join authorof on authorof.isbn = book.isbn";
            customQuery += " join author on author.authorid = authorof.authorid";
            customQuery += " where book.genre = '" + chosenGenre+"'"; 
            customQuery += " and book.isbn like '%" + chosenISBN + "%'";
            customQuery += " and book.title like '%" + chosenTitle + "%'";
            customQuery += " and book.publisher like '%" + chosenPublisher + "%'";
            customQuery += " and book.rating like '%" + chosenRating + "%'";
            customQuery += " and author.authorname like '%" + chosenAuthor + "%'";
            //customQuery += " order by author.authorID ASC";
            customQuery += " order by isbn DESC";
        }

        //System.out.println(customQuery);
        
        try{
          
          aBookList =  new ArrayList<Book>(model.executeQuery2(customQuery));
          
        }catch(SQLException sqla){
            System.out.println(sqla.getMessage());
        }
        
        // eng bg here I guess and use aBookList that you get back to jump 
        // to safe room one with your main thread where you set model.setBookList(aBookList)
        // once you get threads to work
        // until then just use the one below
        model.setBookList(aBookList);
        
        view.bigTableBookScene();
   }
   
   public void handleDeleteBookButton(Button button){
       
      /* if(event.getSource() == deleteButton){
           
       }*/
   }
   
   public void handleSeeReviewsButton(ActionEvent event){
       
   }
   
   public void handleDestroyBook(String anISBN){
        // just make a call and kill any isbn with that number ree
        
        // create bg
        
        System.out.println("you got in maybe");
        if(anISBN.length() == 13){
            String deleteBookQuery = "";
            deleteBookQuery += "Delete from book where isbn = '"
            + anISBN + "'";
            
            
            try{
                model.executeDeleteBook(deleteBookQuery);
            }catch(SQLException sqla){
                System.out.println(sqla.getMessage());
            } catch(Error erra){
                System.out.println(erra.getMessage());
            }
   
        }
        
        // kill bg here

       // view.bigTableBookScene();
       
       
   }
   
    public void handleAddBook(String chosenGenre,String chosenISBN,
           
        String chosenTitle,String chosenPublisher,String chosenAuthor,String chosenRating){
      
        
         //Start bg thread here
       
        /*final javafx.application.Platform.runlater(
        new Runnable(){
            public void run(){
                // do stuff here?
            }
            
        });*/
        
        
        
        boolean goodGenre = false;
        boolean goodRating = false;
        

        if(chosenGenre.matches("Crime")){
           
            goodGenre = true;
        } else if(chosenGenre.matches("Mystery")) {
            
            goodGenre = true;
        } else if(chosenGenre.matches("Romance")) {
            
            goodGenre = true;
        } else if(chosenGenre.matches("Drama")) {
            
            goodGenre = true;
        } else if(chosenGenre.matches("Memoir")) {
           
            goodGenre = true;
        } else if(chosenGenre.matches("Fantasy")) {
           
            goodGenre = true;
        } else if(chosenGenre.matches("Learning")) {
           
            goodGenre = true;           
        } else {
            throw new Error("You didn't enter a viable genre");
            
        }
        if(Book.isValidIsbn(chosenISBN)){
            
        } else{
            throw new Error("bad isbn");
        }
        if(chosenRating.matches("1") || chosenRating.matches("2")
                || chosenRating.matches("3")
                || chosenRating.matches("4")
                || chosenRating.matches("5")){
           goodRating = true;
        }  else{
            throw new Error("bad rating. must be 1-5");
        }
        
        String[] elements = chosenAuthor.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        ArrayList<String> authorsList = new ArrayList<String>(fixedLenghtList);
        /*ArrayList<Author> authorsList = new ArrayList<Author>();
        for(String atts : attList){
            authorsList.add(new Author(atts));
        }*/
        //System.out.println(authorsList);
        
 
        String addBookQuery = "";

        addBookQuery += "Insert into Book values('" + chosenISBN +"','" +
        chosenGenre+"'" + ",'"+chosenTitle+"',"+"'"+chosenPublisher+"','" + chosenRating +"','" +
                model.getUsername() +"')";
        System.out.println(addBookQuery);
       
       if(!authorsList.isEmpty()){
            try{
                model.executeAddBook(addBookQuery,authorsList,chosenISBN);
            }catch(SQLException sqla){
                System.out.println(sqla.getMessage());
            } catch(Error erra){
                System.out.println(erra.getMessage());
            }
        
       } 

        
        //Kill bg thread here
        //Thread dies
        //updateUI
        
        
        
        //Doesn't actually work cus lambda <3
       // view.bigTableBookScene();
    }
    
    

    public void handleAddReview(int chosenBookNr,String chosenBookISBN,
            String chosenTitle,String datext) {
        
        
        //select * from review where isbn = 9789144131757;
        
        

        //addReviewQuery += "select * from review where isbn = '" + addReviewQuery
          //      + "'";
        boolean alreadyReviewed = false;
        for(int i = 0; i<model.getBooks().size();i++){
            if(model.getBooks().get(i).getIsbn().matches(chosenBookISBN)){
                System.out.println(model.getBooks().get(i).getTheReviews());
              /*  if(model.getBooks().get(i).getTheReviews().contains(model.getUserID())){
                    System.out.println(model.getUsername() + " has already reviewed "
                    + chosenBookISBN);
                } else{
                    // create the addReviewUpdate
                    //
                    System.out.println(model.getUsername() + " can review "
                    + chosenBookISBN);
                }*/
              
              for(Review aReview : model.getBooks().get(i).getTheReviews()){
                  if(aReview.getReviewCreatorID().matches(model.getUserID())){
                      alreadyReviewed = true;
                  }
              }
              
                
            }   
        }

        //System.out.println(addReviewQuery);
        if(alreadyReviewed == false){
            
            System.out.println(model.getUsername() + " can review "
            + chosenBookISBN);
            
            String addReviewQuery = "";
            
          
            addReviewQuery += "insert into review(isbn,uid,review_text,reviewcreator) values";
            addReviewQuery += "('" + chosenBookISBN + "'," + model.getUserID() +
            ",'" + datext + "','" + model.getUsername() + "')";
            
            
            try{
                model.executeAddReview(addReviewQuery);
            }catch(SQLException sqla){
                System.out.println(sqla.getMessage());
            } catch(Error erra){
                System.out.println(erra.getMessage());
            }           
            
        } else{
            System.out.println(model.getUsername() + " has already reviewed "
            + chosenBookISBN);
        }
           
        
        view.bigTableReviewScene(chosenBookNr,chosenBookISBN,chosenTitle);
       
        
        
        
    }
    
    

    public void handleDeleteReview(String chosenBookISBN) {

        String deleteReviewQuery = "";
        deleteReviewQuery += "delete from review where isbn = '" + chosenBookISBN
        + "' and uid = " + model.getUserID();
        
        try{
            model.executeDeleteReview(deleteReviewQuery);
        }catch(SQLException sqlad){
            System.out.println(sqlad.getMessage());
        } catch(Error erra){
            System.out.println(erra.getMessage());
        }   
        
    }
    
    

    public void handleAddAuthor(int chosenBookNr, String chosenBookISBN, String chosenTitle, String daAuthor) {
       
        
        String authorID = null;
        boolean authorAlreadyExists = false;
        boolean authorAlreadyExistsInLibrary = false;
        for(int i = 0; i<model.getBooks().size();i++){
            for(Author anAuthor : model.getBooks().get(i).getTheAuthors()){
                  if(anAuthor.getName().matches(daAuthor)){
                      
                      authorAlreadyExistsInLibrary = true;
                      for(int j = 0; j < model.getBooks().get(i).getTheAuthors().size();j++){
                          if(model.getBooks().get(i).getTheAuthors().get(j).getName().matches(daAuthor)){
                             authorID = model.getBooks().get(i).getTheAuthors().get(j).getAuthorID();
                          }
                      }
                      
                  }
              }
            
            if(model.getBooks().get(i).getIsbn().matches(chosenBookISBN)){
                System.out.println(model.getBooks().get(i).getTheAuthors());
            
              
              for(Author anAuthor : model.getBooks().get(i).getTheAuthors()){
                  if(anAuthor.getName().matches(daAuthor)){
                      authorAlreadyExists = true;
                      authorAlreadyExistsInLibrary = true;
                  }
              }
              
                
            }   
        }

        //System.out.println(addReviewQuery);
        if(authorAlreadyExists == false){
            

            System.out.println(daAuthor + " is a new author in "
            + chosenBookISBN);
            
            //need to also check all books incase you need to make a new author
            // or just make an authorof query without making a new author
            
            if(authorAlreadyExistsInLibrary == false){
                //Make a new author and assign an authorof
                //INSERT INTO Author(authorname,authorcreator) VALUES('PeterOhman','jeffrey');

                //INSERT INTO Authorof(isbn) VALUES('9789144131757'); 
                
                String addNewAuthorQuery = "";
                
                //INSERT INTO Author(authorname,authorcreator) VALUES('ChristerAllgulander','fegelein');
                addNewAuthorQuery += "insert into author(authorname,authorcreator) values('" +
                daAuthor +"','" + model.getUsername() +"')";
                
                String addNewAuthorOfQuery = "";
                
                //INSERT INTO Authorof(isbn) VALUES('9789144131757')
                addNewAuthorOfQuery += "insert into authorof(isbn) values('" +
                chosenBookISBN + "')";      
                
                // now execute
                
                try{
                    model.executeAddAuthorAndAuthorOfQuery(addNewAuthorQuery,addNewAuthorOfQuery);
                }catch(SQLException sqla){
                    System.out.println(sqla.getMessage());
                } catch(Error erra){
                    System.out.println(erra.getMessage());
                }  
                
                
                
            }else{
                //just make an authorof
                //INSERT INTO Authorof VALUES('9780261102736',5);
                String addOldAuthorOfQuery = "";
                
                addOldAuthorOfQuery += "insert into authorof values('" +
                chosenBookISBN + "'," + authorID + ")";
                
                //now execute
                try{
                    model.executeAddOnlyAuthorOfQuery(addOldAuthorOfQuery);
                }catch(SQLException sqla){
                    System.out.println(sqla.getMessage());
                } catch(Error erra){
                    System.out.println(erra.getMessage());
                }  
            }
            

            
        } else{
            System.out.println(daAuthor + " already exists in "
            + chosenBookISBN);
        }
        
    }

    public void handleDeleteAuthor(int chosenBookNr, String chosenBookISBN, String chosenTitle, String daID) {

        String deleteAuthorQuery = "";
        deleteAuthorQuery += "delete from author where authorid = " + daID;
        
        try{
            model.executeDeleteAuthor(deleteAuthorQuery);
        }catch(SQLException sqlad){
            System.out.println(sqlad.getMessage());
        } catch(Error erra){
            System.out.println(erra.getMessage());
        }  

    }
    
    
    

    
    
    
}