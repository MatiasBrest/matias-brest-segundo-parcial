

import java.sql.*;

public class MyJDBC02 {

    public static void main(String[] args) {

        Connection connection = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root", "Dragonballsuper1980");

            connection.setAutoCommit(false); // va a permitir ejecutar las sentencias sql en bloques (transacciones)

            Statement statement = connection.createStatement();
            PreparedStatement consultaPreparada = connection.prepareStatement("SELECT * FROM estudiante WHERE apellido=? AND dni BETWEEN ? AND ? ORDER BY apellido");
//
/**/
            String consultaSelect = "SELECT * FROM estudiante";
            ResultSet primerResultSet = statement.executeQuery(consultaSelect);


            while(primerResultSet.next()){
                System.out.println(primerResultSet.getString("id") + " " + primerResultSet.getString("nombre") + " " + primerResultSet.getString("apellido") + " " + primerResultSet.getString("dni"));
            }

            System.out.println();


            statement.executeUpdate("INSERT INTO estudiante (dni, nombre, apellido) VALUES (40643843, 'Matías', 'Brest');");




            ResultSet segundoResultSet = statement.executeQuery(consultaSelect);
            while(segundoResultSet.next()){
                System.out.println(segundoResultSet.getString("id") + " " + segundoResultSet.getString("nombre") + " " + segundoResultSet.getString("apellido") + " " + segundoResultSet.getString("dni"));
            }

            System.out.println();


        }catch(SQLException e){
            // Instrucciones a ejecutar en caso que ocurra la excepción
            System.out.println(e.toString());

            if(connection != null){

                try {
                    connection.rollback();
                } catch (SQLException exception){
                    System.out.println(exception.toString());
                }

            }

        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e){
                System.out.println(e.toString());
            }

        }

    }

}
