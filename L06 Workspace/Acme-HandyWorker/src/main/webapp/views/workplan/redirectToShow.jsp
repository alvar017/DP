<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	 <script type = "text/javascript">
            function Redirect() {
            var url = window.location.href;
            var trozos = url.split("Acme-HandyWorker");
    		var trozo1 = trozos[0];
            
            window.location.replace(trozos[0]+"Acme-HandyWorker${requestScope.urlRedir}${requestScope.id}");
            }  
            Redirect();
      </script>
</body>
</html>