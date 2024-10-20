package com.company.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Random;

@WebServlet("/Servlets/adjacencyMatrix")
public class AdjacencyMatrixServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numVerticesParam = request.getParameter("numVertices");
        String numEdgesParam = request.getParameter("numEdges");

        int numVertices = Integer.parseInt(numVerticesParam);
        int numEdges = Integer.parseInt(numEdgesParam);

        int[][] adjacencyMatrix = GenerateRandomGraph(numVertices, numEdges);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>" + "<head><title>Adjacency Matrix</title></head>");
        out.println("<body>");
        out.println("<h2>Adjacency Matrix</h2>");
        out.println("<table>");
        for (int i = 0; i < numVertices; i++) {
            out.println("<tr>");
            for (int j = 0; j < numVertices; j++) {
                out.println("<td>" + adjacencyMatrix[j][i] + "</td>");
            }
        }
        out.println("</tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
    public int[][] GenerateRandomGraph(int numVertices, int numEdges) {
        int[][] matrix = new int[numVertices][numVertices];
        int currentEdges = 0;

        Random rand = new Random();
        int i = rand.nextInt(numVertices);
        int j = rand.nextInt(numVertices);

        while (currentEdges < numEdges) {
            if (i != j && matrix[i][j] == 0) {
                matrix[i][j] = 1;
                matrix[j][i] = 1;
                currentEdges++;
            }
        }
        return matrix;
    }


}
