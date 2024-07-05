package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueCRUD {
    public class Model {
        private int id;
        private String nome;
        private int quantidade;
        private float valor;
        private String uni_medida;
        private String descricao;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Model(int id, String nome, int quantidade, float valor, String uni_medida, String descricao, Timestamp createdAt, Timestamp updatedAt) {
            this.id = id;
            this.nome = nome;
            this.quantidade = quantidade;
            this.valor = valor;
            this.uni_medida = uni_medida;
            this.descricao = descricao;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public float getValor() {
            return valor;
        }

        public String getUni_medida() {
            return uni_medida;
        }

        public String getDescricao() {
            return descricao;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }
    }

    // Method to insert a new contact
    public int insert(String nome, int quantidade, double valor, String uni_medida, String descricao) {
        String sql = "INSERT INTO `estoque` (`nome`, `quantidade`, `valor`, `und_medida`, `descricao`)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, nome);
            statement.setInt(2, quantidade);
            statement.setDouble(3, valor);
            statement.setString(4, uni_medida);
            statement.setString(5, descricao);

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
        String sql = "SELECT * FROM `estoque`";

        List<Model> estoqueList = new ArrayList<>();

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
                Model estoque = new Model(id, nome, quantidade, valor, uni_medida, descricao, createdAt, updatedAt);
                estoqueList.add(estoque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estoqueList;
    }

    // Method to update a contact
    public boolean update(int id, String nome, int quantidade, double valor, String uni_medida, String descricao) {
        String sql = "UPDATE `estoque` SET `nome` = ?, `quantidade` = ?, `valor` = ?, `und_medida` = ?, `descricao` = ?, `updated_at` = CURRENT_TIMESTAMP WHERE `id` = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setInt(2, quantidade);
            statement.setDouble(3, valor);
            statement.setString(4, uni_medida);
            statement.setString(5, descricao);
            statement.setInt(6, id); // Set the ID parameter

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    // Method to delete a contact
    public boolean delete(int id) {
        String sql = "DELETE FROM `estoque` WHERE `id` = ?";

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

