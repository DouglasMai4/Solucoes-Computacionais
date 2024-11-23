package src.database;

import database.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCRUD {
    public class Model {
        private int id;
        private int customer_id;
        private String description;
        private Boolean finished;
        private Float price;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, int customer_id, String description, Boolean finished, Float price, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.customer_id = customer_id;
            this.description = description;
            this.finished = finished;
            this.price = price;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public String getDescription() {
            return description;
        }

        public Boolean getFinished() {
            return finished;
        }

        public Float getPrice() {
            return price;
        }
    }

    public int insert(int customer_id, String description, Boolean finished, Float price) {
        String sql = "INSERT INTO `orders` (`customer_id`, `description`, `finished`, `price`)" +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, customer_id);
            statement.setString(2, description);
            statement.setBoolean(3, finished);
            statement.setFloat(4, price);

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
        String sql = "SELECT * FROM `orders`";

        List<Model> orderList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                int customer_id = resultSet.getInt("customer_id");

                String description = resultSet.getString("description");

                Boolean finished = resultSet.getBoolean("finished");

                Float price = resultSet.getFloat("price");

                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create object and add to list
                Model order = new Model(
                    id,
                    customer_id,
                    description,
                    finished,
                    price,
                    createdAt,
                    updatedAt
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public boolean update(int id, int customer_id, String description, Boolean finished, Float price) {
        String sql = "UPDATE `orders` SET `customer_id` = ?, `description` = ?, `finished` = ?, `price` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customer_id);
            statement.setString(2, description);
            statement.setBoolean(3, finished);
            statement.setFloat(4, price);
            statement.setInt(5, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM `orders` WHERE `id` = ?";

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
        List<Model> orders = getAll();

        return orders.size();
    }
}

