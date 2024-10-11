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

@WebServlet(urlPatterns = "/put")
public class servletPut extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;

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
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        response.setContentType("application/json;character=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        JsonObject responseObject = new JsonObject();

        if (model.getFromList().containsKey(id)) {
            User user = model.getFromList().get(id);
            user.setName(name);
            user.setSurname(surname);
            user.setSalary(salary);

            responseObject.addProperty("message", "Пользователь с ID: " + id + " успешно обновлен!");
            responseObject.addProperty("name", user.getName());
            responseObject.addProperty("surname", user.getSurname());
            responseObject.addProperty("salary", user.getSalary());
        } else {
            // Если пользователь не найден
            responseObject.addProperty("error", "Пользователь с ID: " + id + " не найден");
        }

        // Возвращаем ответ
        pw.print(gson.toJson(responseObject));

    }


}
