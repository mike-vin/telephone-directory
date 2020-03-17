<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
    <meta charset="utf-8">
</head>
<body>

<h1>Creating new User</h1>

<form action=/user method="post" itemid="user">
    <fieldset>
        <legend>User:</legend>

        <label for="firstName">First name:</label>
        <input type="text" id="firstName" name="firstName"><br><br>

        <label for="secondName">First name:</label>
        <input type="text" id="secondName" name="secondName"><br><br>

        <label for="lastName">Last name:</label>
        <input type="text" id="lastName" name="lastName"><br><br>

        <label for="primaryPhone">Primary phone:</label>
        <input type="tel" id="primaryPhone" name="primaryPhone">

        <label for="primaryPhoneOperator"></label>
        <select id="primaryPhoneOperator" name="primaryPhoneOperator">
            <option hidden>Operator</option>
            <option value="Life" name="Life">Life</option>
            <option value="Kyivstar" name="Kyivstar">Kyivstar</option>
            <option value="UMC" name="UMC">UMC</option>
        </select><br><br>

        <label for="additionalPhone">Additional phone:</label>
        <input type="tel" id="additionalPhone" name="additionalPhone">

        <label for="additionalPhoneOperator"></label>
        <select id="additionalPhoneOperator" name="additionalPhoneOperator">
            <option hidden>Operator</option>
            <option value="Life" name="Life">Life</option>
            <option value="Kyivstar" name="Kyivstar">Kyivstar</option>
            <option value="UMC" name="UMC">UMC</option>
        </select><br><br>

        <input type="submit" value="Submit">

    </fieldset>
</form>

<h3><a href="/user/create/json">Create users by JSON file.</a></h3>

</body>
</html>
