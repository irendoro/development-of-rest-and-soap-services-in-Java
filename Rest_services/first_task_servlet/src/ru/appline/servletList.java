package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class servletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        //блок для чтения данных из тела запроса
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        int id = jobj.get("id").getAsInt();

        response.setContentType("application/json;character=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        JsonObject responseObject = new JsonObject();

        if (id == 0) {
            JsonObject usersList = new JsonObject();
            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
                JsonObject userJson = new JsonObject();
                userJson.addProperty("name", entry.getValue().getName());
                userJson.addProperty("surname", entry.getValue().getSurname());
                userJson.addProperty("salary", entry.getValue().getSalary());
                usersList.add(entry.getKey().toString(), userJson);
            }
            responseObject.add("users", usersList);

        } else if (id > 0) {
            if (model.getFromList().containsKey(id)) {
                User user = model.getFromList().get(id);
                JsonObject userJson = new JsonObject();
                userJson.addProperty("name", user.getName());
                userJson.addProperty("surname", user.getSurname());
                userJson.addProperty("salary", user.getSalary());
                responseObject.add("user", userJson);
            } else {
                responseObject.addProperty("error", " Пользователь не найден");
            }
        } else {
            responseObject.addProperty("error", " ID должен быть >0");
        }

        pw.print(gson.toJson(responseObject));
    }
}
