package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
import org.omg.SendingContext.RunTime;

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
    public Long delete(Long adId) {
        PreparedStatement stmt;
        String sql = "DELETE FROM ads WHERE id=?";
        try{
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getLong(1);
            } else {
                return 0l;
            }

        }catch(SQLException e) {
            throw new RuntimeException("Problem deleting an ad", e);
        }
    }
    @Override
    public Long edit(Long adId, String title, String description) {
        PreparedStatement stmt;
        String sql = "" +
                "UPDATE ads " +
                "SET title = ?, description = ? " +
                "WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, adId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getLong(1);
            } else {
                return 0l;
            }
        } catch(SQLException e) {
            throw new RuntimeException("Problem editing ad", e);
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
//            stmt.setLong(4, ad.getCatId());
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
            rs.getString("description"),
            DaoFactory.getAdAndCatsDao().getCategoriesByAdId(rs.getLong("id")).getCatIds()

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
        Ad noResultsProtected = new Ad(
                noResults.getId(),
                noResults.getTitle(),
                noResults.getDescription());
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
                ads.add(new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        DaoFactory.getAdAndCatsDao().getCategoriesByAdId(rs.getLong("id")).getCatIds()
//                        rs.getLong("cat_id")))
                ));
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
        Ad noResultsProtected = new Ad(
                noResults.getId(),
                noResults.getTitle(),
                noResults.getDescription());
        String query = "SELECT * FROM ads WHERE user_id = ?";
        PreparedStatement stmt;
        List<Ad> adsByUser = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                adsByUser.add(new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")));

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

    @Override
    public List<String> getCatNamesArray(List<Long> catIds){
        List<String>catNames = new ArrayList<>();
        for(Long catId:catIds){
            catNames.add(getCatNameById(catId));
        }
        return catNames;

    }


    @Override
    public String getCatNameById(Long catId) {
        String sql =
                "SELECT name " +
                "FROM categories AS cat " +
                "WHERE cat.id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, catId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            } else {
                return "No category :(";
            }
        } catch (SQLException e) {
            throw new RuntimeException("There was a problem getting the category name by id", e);
        }
    }

    public List<Long> getCatIdsByAdId(Long adId){
        List<Long> catIds = new ArrayList<>();
        String sql = "SELECT DISTINCT category_id FROM ads_categories WHERE ads_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
            catIds.add(rs.getLong("category_id"));
            }
            return catIds;

        }catch (SQLException e){
            throw new RuntimeException("Could not get category id's", e);
        }
    }
}
