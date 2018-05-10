package controller;

import entity.ApiData;
import com.google.gson.Gson;
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
        ApiData<JsonData> data = new Gson().fromJson(json, ApiOneData.class);

        String name = data.getData().getAttributes().get("name").toString();
        String rollNumber = data.getData().getAttributes().get("rollNumber").toString();
        Student student = new Student(System.currentTimeMillis(), name, rollNumber);
        ofy().save().entity(student).now();

        data.setData(JsonData.getInstance(student));

        resp.getWriter().print(new Gson().toJson(data));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiData apiData;

        // Get All Student
        if (req.getPathInfo() == null) {
            apiData = new ApiData<List<JsonData>>();
            List<Student> list = ofy().load().type(Student.class).list();
            List<JsonData> listData = new ArrayList<>();
            for(Student s: list) {
                listData.add(JsonData.getInstance(s));
            }
            apiData.setData(listData);
        }

        //Get one student
        else {
            apiData = new ApiData<JsonData>();
            //System.out.println(req.getPathInfo().replace("/", ""));
            Student s = ofy().load().type(Student.class).id(Long.valueOf(req.getPathInfo().replace("/", ""))).now();
            apiData.setData(JsonData.getInstance(s));
        }

        resp.getWriter().print(new Gson().toJson(apiData));
    }
}
