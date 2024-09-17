<%@ page import="java.sql.*" %> 
<%@ page import="ServletsandHelpers.ConnectionHandler" %> 
<%
    ResultSet result = (ResultSet) request.getAttribute("resultSet");
    String errorMessageMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>CNT 4714 – Project Four – Fall 2023</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
 <style>
  body{ 
      background:#003366; 
  }       

  table {
    width: 50%;
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
  }
  
  tr:nth-child(even) {
      background-color:#ffffff;
  }
  
  
  tr:nth-child(odd) {
      background-color:#ccc;
  }
  
  th {
    background-color: red;
    color: white;
  }   
 </style>
</head>

<body>
 <div class="row" style="text-align: center; margin-top: 10px">
  <h1 style="color: yellow; font-weight: bold; font-size: 35px">Welcome to the Fall 2023 Project 4 Enterprise System</h1>
 </div>
 <div class="row" style="text-align: center;">
  <p style="color:white; font-size:  18px;">You are connected to the Project 4 Enterprise System database as a <i style="color: #00ff00;">root-level</i> user.</p>
 </div>
 <div class="row" style="text-align: center;">
  <p style="color:white; font-size:  18px;">Please enter any SQL query or update command in the box below.</p>
 </div>
 <div class="container">
  <form action="RootUserServlet" method="post">
   <div class="row ">
    <div class="col-sm-3"></div>
    <div class="col-sm-6"> 
     <textarea id="queryInput" style=" background-color: cornsilk;color: crimson" name="queryInput" rows="10" cols="79"></textarea> </div>
   </div>
   <div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-2"> <input type="submit" value="Execute Command" class="btn btn-primary" style=" font-size: 22px; font-weight: bold; background:#006666;color: #00cc33"> </div>
    <div class="col-sm-2"> <input id="clearForm" type="button" value="Reset Form" class="btn btn-primary" style=" font-size: 22px; font-weight: bold; background:#006666;color: red;margin-left: 40px"> </div>
    <div class="col-sm-2"> <input type="button" id="clearButton" value="Clear Results" class="btn btn-primary" style=" font-size: 22px; font-weight: bold; background:#006666;color:yellow"> </div>
   </div>
  </form>
 </div>
 <div class="row" style="text-align: center;margin-top: 20px">
  <p style="color:white; font-size:  20px;">All execution results will appear below this line.</p>
 </div>
 <hr>
 <div class="row" style="text-align: center;margin-top: 20px">
  <p style="color:white; font-size:  24px; font-weight: bold">Execution Results: </p>
 </div>
 <div class="container"> <% if (result != null) { %> 
    <table id="resultsTable"> 
    <% 
    ResultSetMetaData metaData = result.getMetaData();
    int columnCount = metaData.getColumnCount();
    %> 
    <tr> <% for (int i = 1; i <= columnCount; i++) { %> 
    <th><%= metaData.getColumnName(i) %></th> <% } %> 
    </tr> <% while (result.next()) { %> <tr> 
    <% for (int i = 1; i <= columnCount; i++) { %> 
    <td><%= result.getString(i) %></td> <% } %> </tr> 
    <% } %> </table> <% } else if (successMessage != null) { %> 
    <div id="successMessage" style="background: forestgreen; color: white; font-size: 20px; font-weight: bold; text-align: center"> 
    <%= successMessage %> </div> <% } 
    else if (errorMessageMessage != null) { %> 
    <div id="errorMessage" style="background: red; color: white; font-size: 20px; font-weight: bold; text-align: center"> 
    <%= errorMessageMessage %> </div> <% } %> 
 </div>
</body>
<script>
   document.getElementById('clearButton').addEventListener('click', () => {
   document.getElementById('resultsTable').innerHTML = '';
   document.getElementById('errorMessage').innerHTML = '';
   document.getElementById('successMessage').innerHTML = '';
 });
 
   document.getElementById('clearForm').addEventListener('click', () => {
   document.getElementById('queryInput').value = '';
 });
</script>
</html>