package src.database;

import database.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseCRUD {
    public class Model {
        private int id;
        private int customer_id;
        private int product_id;
        private int quantity;
        private boolean finished;
        private float price;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, int customer_id, int product_id, int quantity, boolean finished, float price, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.customer_id = customer_id;
            this.product_id = product_id;
            this.quantity = quantity;
            this.finished = finished;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public boolean getFinished() {
            return finished;
        }

        public float getPrice() {
            return price;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }
    }

    public int insert(int customer_id, int product_id, int quantity, boolean finished, float price) {
        String sql = "INSERT INTO `purchases` (`customer_id`, `product_id`, `quantity`, `finished`, `price`)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, customer_id);
            statement.setInt(2, product_id);
            statement.setFloat(3, quantity);
            statement.setBoolean(4, finished);
            statement.setFloat(5, price);

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
        String sql = "SELECT * FROM `purchases`";

        List<Model> purchaseList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                int customer_id = Integer.parseInt(resultSet.getString("customer_id"));

                int product_id = Integer.parseInt(resultSet.getString("product_id"));

                int quantity = Integer.parseInt(resultSet.getString("quantity"));

                boolean finished = Boolean.parseBoolean(resultSet.getString("finished"));

                float price = Float.parseFloat(resultSet.getString("price"));

                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create object and add to list
                Model purchase = new Model(
                    id,
                    customer_id,
                    product_id,
                    quantity,
                    finished,
                    price,
                    createdAt,
                    updatedAt
                );

                purchaseList.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchaseList;
    }

    public boolean update(int id, int customer_id, int product_id, int quantity, boolean finished, float price) {
        String sql = "UPDATE `purchases` SET `customer_id` = ?, `product_id` = ?, `quantity` = ?, `finished` = ?, `price` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customer_id);
            statement.setInt(2, product_id);
            statement.setInt(3, quantity);
            statement.setBoolean(4, finished);
            statement.setFloat(5, price);
            statement.setInt(6, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM `purchases` WHERE `id` = ?";

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

