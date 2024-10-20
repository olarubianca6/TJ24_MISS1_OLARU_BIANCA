package com.company.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serial;

@WebServlet("/Servlets/stringToList")
public class StringToListServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String input = request.getParameter("input");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>String to Character List</title></head>");
        out.println("<body>");
        out.println("<h1>Characters in the input string:</h1>");

        if (input != null && !input.isEmpty()) {
            out.println("<ol>");
            for (int i = 0; i < input.length(); i++) {
                char character = input.charAt(i);
                out.println("<li>" + character + "</li>");
            }
            out.println("</ol>");
        } else {
            out.println("<p>No input provided.</p>");
        }

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
