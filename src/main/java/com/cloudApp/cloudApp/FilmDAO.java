package com.cloudApp.cloudApp;


import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

@Repository
public class FilmDAO {
    Film film = new Film();
    Film oneFilm = null;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public FilmDAO() {
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Film> getAllFilms() {
        ArrayList<Film> allFilms = new ArrayList<>();
        try {

            conn = DataSourceConfig.dataSource().getConnection();
            String selectSQL = "SELECT * FROM films";
            preparedStatement = conn.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                oneFilm = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getString("director"),
                        resultSet.getString("stars"),
                        resultSet.getString("review")
                );
                allFilms.add(oneFilm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return allFilms;
    }
    public Film addFilm( Film film){

        try{
            String insertSQL= "INSERT INTO films (title, year, director, stars, review) VALUES (?, ?, ?, ?, ?)";
            //ResultSet rs1 = stmt.executeQuery(inserySQL);
            conn = DataSourceConfig.dataSource().getConnection();
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
            closeResources();
        }
    }

    public void updateFilmByID(int id, int year, String director, String review) {
        try {
            String checkExistsSQL = "SELECT COUNT(*) FROM films WHERE id=?";
            conn = DataSourceConfig.dataSource().getConnection();
            PreparedStatement checkIfFilmExists = conn.prepareStatement(checkExistsSQL);
            checkIfFilmExists.setInt(1, id);
            ResultSet resultSet = checkIfFilmExists.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count == 0) {
                System.out.println("Film with ID " + id + " does not exist");
                return;
            }

            String updateSQL = "UPDATE films SET director=?, year=?, review=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
            preparedStatement.setString(1, director);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3, review);
            preparedStatement.setInt(4, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Film with ID " + id + " updated successfully");
            } else {
                System.out.println("Failed to update film with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void deleteFilmByTitle(String title){

        try {
            String deleteFilmSQL = "DELETE FROM films WHERE title=?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteFilmSQL);
            preparedStatement.setString(1,title);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0){
                System.out.print("Deleted Successfully");
            } else {
                System.out.print("Failed to delete");
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public Film getFilmByID(int id) {
        Film film = null;
        try {
            conn = DataSourceConfig.dataSource().getConnection();
            String selectSQL = "SELECT * FROM films WHERE id=?";
            preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getString("director"),
                        resultSet.getString("stars"),
                        resultSet.getString("review")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return film;
    }

}