package estagio_instruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author sanchear
 */
public class Estagio_instruct {

    public static String getText(String url) throws Exception {

        //Acessar URL para captação de Array
        URL instruct = new URL(url);
        URLConnection connection = instruct.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));

        StringBuilder resposta = new StringBuilder();
        String entradaLinha;

        while ((entradaLinha = in.readLine()) != null) {
            resposta.append(entradaLinha);
        }

        in.close();

        return resposta.toString();
    }

    public static void main(String[] args) throws Exception {

        //Variável que será utilizada para contar os moradores do HS
        int cont2 = 0;

        //Captação de Array via URL
        JSONArray jsonArray = new JSONArray(getText("https://jsonplaceholder.typicode.com/users"));

        System.out.println("Segue lista de websites dos cadastrados:\n");
        for (int i = 0; i < jsonArray.length(); i++) {

            //Captação de objeto ({) de Array ([)
            JSONObject obj = jsonArray.getJSONObject(i);
            //Captando a string do objeto afim de imprimí-la no console
            String website = obj.getString("website");
            System.out.println(website);

        }

        System.out.println("\n\nSegue e-mail da Samantha para consulta:\n");
        for (int j = 0; j < jsonArray.length(); j++) {

            //get the JSON Object 
            JSONObject obj2 = jsonArray.getJSONObject(j);
            //Captando a string afim de comparar o texto com o desejado
            String username = obj2.getString("username");
            //Captando a string do objeto afim de imprimí-la no console
            JSONObject obj3 = jsonArray.getJSONObject(j);
            String email = obj3.getString("email");

            //Teste lógico para encontrar a usuária Samantha e informar seu e-mail
            if (username.equals("Samantha")) {
                System.out.println(email);
            }
        }

        System.out.println("\n\nPara fins informativos, de todos os registrados, o total daqueles"
                + "que moram no Hemisfério Sul é igual a:");
        for (int i = 0; i < jsonArray.length(); i++) {

            //Variável para checagem de caracteres
            int cont = 0;

            //Obtenção de ramificação dos objetos
            JSONObject obj = jsonArray.getJSONObject(i);
            JSONObject address = obj.getJSONObject("address");
            JSONObject geo = address.getJSONObject("geo");
            //Captando a string do objeto afim de verificar a moradia rs
            String lat = geo.getString("lat");
            //Checagem de presença de caractere
            cont = lat.indexOf("-");

            //Teste para adicionar ao contador os usuários do HS
            if (cont == 0) {
                cont2++;
            }
        }

        System.out.println(cont2);

    }

}
