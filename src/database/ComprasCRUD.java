package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprasCRUD {
    public class Model {
        private int id;
        private int cliente_id;
        private int product_id;
        private int quantity;
        private int finalizado;
        private double valor;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, int cliente_id, int product_id, int quantity, int finalizado, double valor, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.cliente_id = cliente_id;
            this.product_id = product_id;
            this.quantity = quantity;
            this.finalizado = finalizado;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.valor = valor;
        }

        public int getId() {
            return id;
        }

        public int getCliente_id() {
            return cliente_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getFinalizado() {
            return finalizado;
        }

        public double getValor() {
            return valor;
        }
    }

        // Method to insert a new contact
    public int insert(String nome, String endereco, String telefone, String cidade, String estado, String cpf) {
        String sql = "INSERT INTO `clientes` (`nome`, `endereco`, `telefone`, `cidade`, `estado`, `cpf`)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, nome);
            statement.setString(2, endereco);
            statement.setString(3, telefone);
            statement.setString(4, cidade);
            statement.setString(5, estado);
            statement.setString(6, cpf);

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

    // Method to retrieve all contacts
    public List<Model> getAll() {
        String sql = "SELECT * FROM `compras`";

        List<Model> estoqueList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                int cliente_id = Integer.parseInt(resultSet.getString("cliente_id"));

                int product_id = Integer.parseInt(resultSet.getString("product_id"));

                int quantity = Integer.parseInt(resultSet.getString("quantity"));

                int finalizado = Integer.parseInt(resultSet.getString("finalizado"));

                double valor = Integer.parseInt(resultSet.getString("valor"));

                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create object and add to list
                Model cliente = new Model(id, cliente_id, product_id, quantity, finalizado, valor, createdAt, updatedAt);
                estoqueList.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoqueList;
    }

    // Method to update a contact
    public boolean update(int id, String nome, String endereco, String cidade, String estado, String cpf, String telefone) {
        String sql = "UPDATE `clientes` SET `nome` = ?, `endereco` = ?, `telefone` = ?, `cidade` = ?, `estado` = ?, `cpf` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setString(2, endereco);
            statement.setString(3, telefone);
            statement.setString(4, cidade);
            statement.setString(5, estado);
            statement.setString(6, cpf);
            statement.setInt(7, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    // Method to delete a contact
    public boolean delete(int id) {
        String sql = "DELETE FROM `clientes` WHERE `id` = ?";

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

