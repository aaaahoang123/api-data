package controller;

import com.google.gson.Gson;
import com.sun.javafx.iio.ios.IosDescriptor;
import entity.ApiListData;
import entity.ApiOneData;
import entity.JsonData;
import entity.Student;
import utility.BodyParser;
import static com.googlecode.objectify.ObjectifyService.ofy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = BodyParser.parseBodyToJson(req.getReader());
        ApiOneData data = new Gson().fromJson(json, ApiOneData.class);

        String name = data.getData().getAttributes().get("name").toString();
        String rollNumber = data.getData().getAttributes().get("rollNumber").toString();
        Student student = new Student(System.currentTimeMillis(), name, rollNumber);
        ofy().save().entity(student).now();

        data.setData(JsonData.getInstance(student));

        resp.getWriter().print(new Gson().toJson(data));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiListData data = new ApiListData();

        List<Student> list = ofy().load().type(Student.class).list();
        List<JsonData> listData = new ArrayList<>();
        for(Student s: list) {
            listData.add(JsonData.getInstance(s));
        }
        data.setData(listData);
        throw new IOException("Ngu vl");
        //resp.getWriter().print(new Gson().toJson(null));
    }
}
