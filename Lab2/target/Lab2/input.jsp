<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="com.google.code.kaptcha.Producer" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>

<%
    request.getRequestDispatcher("/init").include(request, response);

    Producer captchaProducer = (Producer) application.getAttribute("captchaProducer");

    if (captchaProducer == null) {
        throw new IllegalStateException("Captcha producer is not initialized.");
    }

    String captchaText = captchaProducer.createText();
    session.setAttribute("captcha", captchaText);

    BufferedImage captchaImage = captchaProducer.createImage(captchaText);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(captchaImage, "jpg", baos);
    byte[] captchaBytes = baos.toByteArray();
    String captchaBase64 = Base64.getEncoder().encodeToString(captchaBytes);
%>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<h1>Upload a Text File</h1>
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept=".txt" required>
    <div>
        <%--@declare id="captcha"--%>
        <label for="captcha">Enter the CAPTCHA: </label>
        <img src="data:image/jpg;base64,<%= captchaBase64 %>" alt="CAPTCHA Image" />
        <input type="text" name="captcha" required>
    </div>
    <input type="submit" value="upload">
</form>
</body>
</html>