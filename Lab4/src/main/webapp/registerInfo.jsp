<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Info</title>
    <style>
        li{
            list-style: none;
        }
        
    </style>
    
</head>

<body>
    <table>
        <tr>
            <td>Họ tên</td>
            <td><%= request.getParameter("name") %> </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><%= request.getParameter("email") %> </td>
        </tr>
        <tr>
            <td>Ngày sinh</td>
            <td><%= request.getParameter("birthday") %> </td>
        </tr>
        <tr>
            <td>Giới tính</td>
            <td><%= request.getParameter("gender") %> </td>
        </tr>
        <tr>
            <td>Quốc gia</td>
            <td><%= request.getParameter("country") %> </td>
        </tr>
        <tr>
            <td>IDE Yêu thích</td>
            <td>
                <ul>
                    <% String[] IDEs =  request.getParameterValues("favorite_ide[]");
    
                        for(String IDE : IDEs) {
                            out.println("<li>" + IDE + "</li>");
                        }
                    %>
                </ul>
             </td>
        </tr>
        <tr>
            <td>Điểm TOEIC</td>
            <td><%= request.getParameter("toeic") %> </td>
        </tr>
        <tr>
            <td>Giới thiệu bản thân</td>
            <td><%= request.getParameter("message") %> </td>
        </tr>
    </table>
</body>
</html>