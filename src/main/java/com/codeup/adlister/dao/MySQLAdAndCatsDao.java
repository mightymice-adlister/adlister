package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdIdAndCatId;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdAndCatsDao implements AdAndCats {
    private Connection connection = null;
    public MySQLAdAndCatsDao(Config config) {
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
    public void editCat(AdIdAndCatId aici) {
        delete(aici.getAdId());
        insert(aici);
    }


    private Long delete(Long adId) {
        PreparedStatement stmt;
        String sql = "DELETE FROM ads_categories WHERE ads_id=?";
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
            throw new RuntimeException("Problem deleting ads/categories", e);
        }
    }

    @Override
    public void insert(AdIdAndCatId AandC){
        for(Long catId: AandC.getCatIds())
        try {
            String insertQuery = "INSERT INTO ads_categories(ads_id, category_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, AandC.getAdId());
            stmt.setLong(2, catId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public AdIdAndCatId getCategoriesByAdId(Long adId) {
        try{
            String sql = "SELECT * FROM ads_categories Where ads_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, adId);
            ResultSet rs = statement.executeQuery();
            List<Long> catIds = new ArrayList<>();
            while(rs.next()){
                catIds.add(rs.getLong("category_id"));
            }
            return new AdIdAndCatId(adId, catIds);
        }catch (SQLException e){
            throw new RuntimeException("Could not get categories from Ad ID", e);
        }
    }
}

