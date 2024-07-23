package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String jdbcUrl = "jdbc:mysql://localhost:3306/patricia"; // Remplace par le nom de ta base de données
        String username = "root"; // Remplace par ton nom d'utilisateur
        String password = ""; // Remplace par ton mot de passe

        Connection connection = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connexion réussie !");

            // Créer une table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS utilisateurs ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Nom VARCHAR(20) NOT NULL, "
                    + "PostNom VARCHAR(20) NOT NULL, "
                    + "Prenom VARCHAR(10) NOT NULL"
                    + ")";
            
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table 'utilisateurs ' créée avec succès !");
            
            // Insérer des données dans la table
            String insertDataSQL = "INSERT INTO utilisateurs (Nom, Postnom, Prenom) VALUES "
                    + "('Tshibola', 'bayamba', 'Tichaou'), "
                    + "('Lukanda', 'Luboya', 'Joe'), "
                    + "('kaisa', 'Mumba', 'Babi')";

            
            statement.executeUpdate(insertDataSQL);
            System.out.println("Données insérées avec succès !");
            
            // Supprimer user1 de la table
            String deleteUserSQL = "DELETE FROM utilisateurs WHERE Nom = 'Lukanda'";
            statement.executeUpdate(deleteUserSQL);
            
            System.out.println("Utilisateur 'utilisateur  Lukanda ' supprimé avec succès !");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : Driver JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur : Impossible de se connecter à la base de données ou erreur SQL !");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture de la connexion !");
                    e.printStackTrace();
                }
            }
        }
    }
}
