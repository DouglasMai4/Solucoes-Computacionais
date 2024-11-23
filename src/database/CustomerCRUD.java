package src.database;

import database.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCRUD {
    public class Model {
        private int id;
        private String name;
        private int phone;
        private int document;
        private String address;
        private String city;
        private String state;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, String name, int phone, int document, String address, String city, String state, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.document = document;
            this.address = address;
            this.city = city;
            this.state = state;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPhone() {
            return phone;
        }

        public int getDocument() {
            return document;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }
    }

    public int insert(String name, int phone, int document, String address, String city, String state) {
        String sql = "INSERT INTO `customers` (`name`, `phone`, `document`, `address`, `city`, `state`)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setInt(2, phone);
            statement.setInt(3, document);
            statement.setString(4, address);
            statement.setString(5, city);
            statement.setString(6, state);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        System.out.println("Inserted successfully. Generated ID: " + id);
                        return id;
                    } else {
                        System.out.println("No ID obtained.");
                        return -1; // Indicate no ID was obtained
                    }
                }
            } else {
                System.out.println("Insertion failed.");
                return -1; // Indicate insertion failed
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public List<Model> getAll() {
        String sql = "SELECT * FROM `customers`";

        List<Model> customerList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                String name = resultSet.getString("name");

                int phone = resultSet.getInt("phone");

                int document = resultSet.getInt("document");

                String address = resultSet.getString("address");

                String city = resultSet.getString("city");

                String state = resultSet.getString("state");

                Timestamp createdAt = resultSet.getTimestamp("created_at");

                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create object and add to list
                Model customer = new Model(id, name, phone, document, address, city, state, createdAt, updatedAt);

                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public boolean update(int id, String name, int phone, int document, String address, String city, String state) {
        String sql = "UPDATE `customers` SET `name` = ?, `phone` = ?, `document` = ?, `address` = ?, `city` = ?, `state` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, phone);
            statement.setInt(3, document);
            statement.setString(4, address);
            statement.setString(5, city);
            statement.setString(6, state);
            statement.setInt(7, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM `customers` WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public int getCount() {
        List<Model> itens = getAll();

        return itens.size();
    }
}

