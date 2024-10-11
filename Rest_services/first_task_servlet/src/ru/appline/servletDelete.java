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

@WebServlet(urlPatterns = "/delete")
public class servletDelete extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        if (model.getFromList().containsKey(id)) {
            model.getFromList().remove(id);
            responseObject.addProperty("message", " Пользователь с " + id + " успешно удален");
        } else {
            responseObject.addProperty("error", " Пользователь с ID: " + id + " не найден");
        }

        pw.print(gson.toJson(responseObject));
    }

}
