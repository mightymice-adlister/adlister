package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;
    private Ad noResults = new Ad(0L, "None", "Could not find results for: ");

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Ad viewOneAd(Long adId){
        PreparedStatement stmt;
        String sql = "SELECT * FROM ads WHERE id = ?";
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);

        }catch(SQLException e){
            throw new RuntimeException("could not find ad.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }


    @Override
    public List<Ad> searchAll(String terms) {
        Ad noResultsProtected = new Ad(noResults.getId(), noResults.getTitle(), noResults.getDescription());
        PreparedStatement stmt;
        String termsWildCard = "%"+terms+"%";
        String query = "SELECT * FROM ads WHERE description LIKE ? OR title LIKE ?";

        List<Ad> ads = new ArrayList<>();
        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, termsWildCard);
            stmt.setString(2, termsWildCard);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ads.add(new Ad(rs.getLong("id"), rs.getLong("user_id"), rs.getString("title"), rs.getString("description")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if (ads.isEmpty()){
            noResultsProtected.setDescription(noResultsProtected.getDescription()+terms);
            ads.add(noResultsProtected);
        }
        return ads;

    }

    @Override
    public List<Ad> viewAdsByUser(Long userId) {
        Ad noResultsProtected = new Ad(noResults.getId(), noResults.getTitle(), noResults.getDescription());
        String query = "SELECT * FROM ads WHERE user_id = ?";
        PreparedStatement stmt;
        List<Ad> adsByUser = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                adsByUser.add(new Ad(rs.getLong("id"), rs.getLong("user_id"), rs.getString("title"), rs.getString("description")));

            }
        }catch(SQLException e){
            throw new RuntimeException("Could not get ads for this user", e);
        }
        if (adsByUser.isEmpty()){
            noResultsProtected.setDescription(noResultsProtected.getDescription()+" from user with id:"+userId);
            adsByUser.add(noResultsProtected);
        }

        return adsByUser;
    }
}
