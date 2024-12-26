<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<html>
<head>
    <title>방명록</title>
</head>
<body>
<form method="post" action="/guestbook03/delete/${id}">
    <table>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password"></td>
            <td><input type="submit" value="확인"></td>
        </tr>
    </table>
</form>
<br>
<a href="/guestbook03/">메인으로 돌아가기</a>
</body>
</html>
