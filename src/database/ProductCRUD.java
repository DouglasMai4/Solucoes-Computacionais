package src.database;

import database.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCRUD {
    public class Model {
        private int id;
        private String name;
        private int quantity;
        private float price;
        private String measurement_unit;
        private String description;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, String name, int quantity, Float price, String measurement_unit, String description, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.measurement_unit = measurement_unit;
            this.description = description;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public Float getPrice() {
            return price;
        }

        public String getMeasurement_unit() {
            return measurement_unit;
        }

        public String getDescription() {
            return description;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }
    }

    public int insert(String name, int quantity, Float price, String measurement_unit, String description) {
        String sql = "INSERT INTO `products` (`name`, `quantity`, `price`, `measurement_unit`, `description`)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
            statement.setString(4, measurement_unit);
            statement.setString(5, description);

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
        String sql = "SELECT * FROM `products`";

        List<Model> productsList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int quantidade = resultSet.getInt("quantidade");
                float valor = resultSet.getFloat("valor");
                String uni_medida = resultSet.getString("und_medida");
                String descricao = resultSet.getString("descricao");
                // Assuming you have timestamp columns created_at and updated_at
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create Estoque object and add to list
                Model products = new Model(id, nome, quantidade, valor, uni_medida, descricao, createdAt, updatedAt);
                productsList.add(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsList;
    }

    public boolean update(int id, String name, int quantity, Float price, String measurement_unit, String description) {
        String sql = "UPDATE `products` SET `name` = ?, `quantity` = ?, `price` = ?, `measurement_unit` = ?, `description` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setFloat(3, price);
            statement.setString(4, measurement_unit);
            statement.setString(5, description);
            statement.setInt(6, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM `products` WHERE `id` = ?";

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

