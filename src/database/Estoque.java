package database;

import java.sql.*;

class EstoqueProps {
     int id = 0;
     String name = "";
     int quantidade = 0;
     double valor = 0.0;
     String und_medida = "";
     String descricao = "";
     String created_at = "";
     String updated_at = "";
}

public class Estoque {
    // Method to insert a new contact
    public void insert(String nome, int quantidade, double valor, String uni_medida, String descricao) {
        String sql = "INSERT INTO automotive-system (nome, quantidade, valor, uni_medida, descricao) VALUES (?, ?, ?, ?)";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setInt(2, quantidade);
            statement.setDouble(3, valor);
            statement.setString(4, uni_medida);
            statement.setString(5, descricao);

            statement.executeUpdate();
            System.out.println("Adicionado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all contacts
    public void getAll() {
        String sql = "SELECT * FROM automotive-system";

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String phone = resultSet.getString("phone");
                int idade = resultSet.getInt("idade");
                String cpf = resultSet.getString("cpf");

                System.out.println("Nome: " + nome + ", Phone: " + phone + ", Idade: " + idade + ", CPF: " + cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a contact
    public void update(String nome, String newPhone, int newIdade, String newCpf) {
        String sql = "UPDATE automotive-system SET phone = ?, idade = ?, cpf = ? WHERE nome = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPhone);
            statement.setInt(2, newIdade);
            statement.setString(3, newCpf);
            statement.setString(4, nome);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a contact
    public void delete(String nome) {
        String sql = "DELETE FROM automotive-system WHERE nome = ?";

        try (Connection connection = Connect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

