<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Content Shuffled</title>
</head>
<body>
<h2>Shuffled File Content</h2>
<%
    List<String> lines = (List<String>) session.getAttribute("fileLines");
    if (lines != null) {
        Collections.shuffle(lines);
        for (String line : lines) {
            out.println("<p>" + line + "</p>");
        }
    } else {
        out.println("<p>No file uploaded</p>");
    }
%>
</body>
</html>