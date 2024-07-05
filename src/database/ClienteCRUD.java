package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteCRUD {
    public class Model {
        private int id;
        private String nome;
        private String endereco;
        private String cidade;
        private String estado;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private String cpf;
        private String telefone;

        public Model(int id, String nome, String endereco, String cidade, String estado, String telefone, String cpf, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.nome = nome;
            this.endereco = endereco;
            this.cidade = cidade;
            this.estado = estado;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.cpf = cpf;
            this.telefone = telefone;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getCidade() {
            return cidade;
        }

        public String getEstado() {
            return estado;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }

        public String getCpf() {
            return cpf;
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
        String sql = "SELECT * FROM `clientes`";

        List<Model> estoqueList = new ArrayList<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                String nome = resultSet.getString("nome");

                String endereco = resultSet.getString("endereco");

                String cidade = resultSet.getString("cidade");

                String estado = resultSet.getString("estado");

                String cpf = resultSet.getString("cpf");

                String telefone = resultSet.getString("telefone");

                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                // Create object and add to list
                Model cliente = new Model(id, nome, endereco, cidade, estado, telefone, cpf, createdAt, updatedAt);
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

