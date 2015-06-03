<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Création Car</title>
</head>
<body>

	<form action="/jwf/car/create" method="post">
		<label for="name">Name: </label> <input name="name"><br>
		<label for="price">Price: </label> <input name="price"><br>
		<label for="year">Year: </label> <input name="year"><br>
		<label for="type_fuel">Type of fuel: </label> <input name="type_fuel"><br>
		<label for="gearbox_type">Type of gearbox: </label> <input name="gearbox_type"><br>
		<label for="description">Description: </label> <input name="description"><br>
		<label for="telephone_number">Telephone number: </label> <input name="telephone_number"><br>
		<label for="email">Email: </label> <input name="email"><br>
		<input type="submit" value=Créer>
	</form>

</body>
</html>