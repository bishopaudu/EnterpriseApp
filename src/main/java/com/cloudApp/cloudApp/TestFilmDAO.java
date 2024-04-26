/*package com.cloudApp.cloudApp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;


public class TestFilmDAO {
	Film film = new Film();
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "adeyemoy";
    String password = "qulsrng4";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;



	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }

   public Film addFilm( Film film){
		openConnection();
		try{
			String insertSQL= "INSERT INTO films (title, year, director, stars, review) VALUES (?, ?, ?, ?, ?)";
			//ResultSet rs1 = stmt.executeQuery(inserySQL);
			PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
			preparedStatement.setString(1,film.getTitle());
			preparedStatement.setInt(2,film.getYear());
			preparedStatement.setString(3,film.getDirector());
			preparedStatement.setString(4,film.getStars());
			preparedStatement.setString(5,film.getReview());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0){
				System.out.print("now rows affected");
			} else {
				System.out.print("failed to add new film");
			}
			return null;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
   }

   public void updateFilmByTitle(String title,int year,String director,String review){
		openConnection();
		try{
			String checkExistsSQL = "SELECT COUNT(*) FROM films WHERE title=?";
			PreparedStatement checkIfFilmExists = conn.prepareStatement(checkExistsSQL);
			checkIfFilmExists.setString(1,title);
			ResultSet resultSet = checkIfFilmExists.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);
			if (count == 0){
				System.out.print("film with the title:" + title + "does not exists");
				return;
			}
			String updateSQL = "UPDATE films SET director=?, year=?, review=? WHERE title=?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
			preparedStatement.setString(1,director);
			preparedStatement.setString(2,review);
			preparedStatement.setInt(3,year);
			preparedStatement.setString(4,title);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0){
				System.out.print("film update successfully");
			} else {
				System.out.print("fail to update");
			}
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
   }

   public void deleteFilmByTitle(String title){
		openConnection();
		try {
			String deleteFilmSQL = "DELETE FROM films WHERE title=?";
			PreparedStatement preparedStatement = conn.prepareStatement(deleteFilmSQL);
			preparedStatement.setString(1,title);
			int rowsDeleted = preparedStatement.executeUpdate();
			if (rowsDeleted > 0){
				System.out.print("Deleted Suceessfully");
			} else {
				System.out.print("Failed to delete");
			}
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
   }
}*/
