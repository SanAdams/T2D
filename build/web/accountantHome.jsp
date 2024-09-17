<%@ page import="java.sql.*" %> 
<%@ page import="ServletsandHelpers.ConnectionHandler" %> 
<%
    ResultSet tableDataResult = (ResultSet) request.getAttribute("resultSet");
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");

%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <title>CNT 4714 – Project Four – Fall 2023</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
 <style>
  body{ 
      background:#660066;  
  }       
    
   input[type="radio"] {
   width: 20px;
   height: 20px;
   margin-top: 13px;
   margin-left: 50px;
   border-radius: 50%;
   background-color: #ccc;
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
  <p style="color:white; font-size:  18px;">You are connected to the Project 4 Enterprise System database as an <i style="color: cadetblue;">accountant-level</i> user.</p>
 </div>
 <div class="container" style="background: chocolate;">
  <form method="post" action="AccountantUserServlet">
   <div class="row">
    <div class="col-sm-1"> <input type="radio" id="q1" name="selection" value="Get_The_Maximum_Status_Of_All_Suppliers"> </div>
    <div class="col-sm-11"> <label for="q1" style="color: black; font-size: 24px;"> <span style="color: #990099;">Get The Maximum Status Value Of All Suppliers </span></label> </div>
   </div>
   <div class="row">
    <div class="col-sm-1"> <input type="radio" id="q2" name="selection" value="Get_The_Sum_Of_All_Parts_Weights"> </div>
    <div class="col-sm-11"> <label for="q2" style="color: black; font-size: 24px;"> <span style="color: #990099;">Get The Total Weight Of All Parts </span></label> </div>
   </div>
   <div class="row">
    <div class="col-sm-1"> <input type="radio" id="q3" name="selection" value="Get_The_Total_Number_Of_Shipments"> </div>
    <div class="col-sm-11"> <label for="q3" style="color: black; font-size: 24px;"> <span style="color: #990099;"> Get The Total Number of Shipments </span></label> </div>
   </div>
   <div class="row">
    <div class="col-sm-1"> <input type="radio" id="q4" name="selection" value="Get_The_Name_Of_The_Job_With_The_Most_Workers"> </div>
    <div class="col-sm-11"> <label for="q4" style="color: black; font-size: 24px;"> <span style="color: #990099;"> Get The Name And Number Of Workers Of The Job With The Most Workers </span></label> </div>
   </div>
   <div class="row">
    <div class="col-sm-1"> <input type="radio" id="q5" name="selection" value="List_The_Name_And_Status_Of_All_Suppliers"> </div>
    <div class="col-sm-11"> <label for="q5" style="color: black; font-size: 24px;"> <span style="color: #990099;">List The Name And Status Of Every Supplier </span></label> </div>
   </div> <input type="submit" value="Execute Command" style="margin-left: 300px;color:lawngreen; background-color: #006666; font-size: 22px; font-weight: bold; margin-top:  20px; margin-bottom: 20px; "> <input type="button" id="clearButton" value="Clear Results" style="margin-left: 500px;color:red; background-color: #006666; font-size: 22px; font-weight: bold; margin-top:  20px; margin-bottom: 20px; ">
  </form>
 </div>. <div class="row" style="text-align: center;margin-top: 20px">
  <p style="color:white; font-size:  20px;">All execution results will appear below this line.</p>
 </div>
 <hr>
 <div class="row" style="text-align: center;margin-top: 20px">
  <p style="color:white; font-size:  24px; font-weight: bold">Execution Results: </p>
 </div>
 <div class="container"> 
     <% if (tableDataResult != null) { %> <table id = "resultsTable"> <% 
        ResultSetMetaData metaData = tableDataResult.getMetaData();
        int cols = metaData.getColumnCount();
     %> <tr> <% for (int i = 1; i <= cols; i++) { %> 
        <th><%= metaData.getColumnName(i) %></th> <% } %> 
        </tr> <% while (tableDataResult.next()) { %> <tr> 
        <%for (int i = 1; i <= cols; i++) { %> 
        <td><%= tableDataResult.getString(i) %></td> <% } %> 
        </tr> <% } %> </table> <% } else if (successMessage != null) { %> 
        <div id="successMessage" style="background: forestgreen; color: white; font-size: 20px; font-weight: bold; text-align: center"> 
        <%= successMessage %> </div> 
        <% } else if (errorMessage != null) { %> <div id="errorMessage" style="background: red; color: white; font-size: 20px; font-weight: bold; text-align: center"> 
        <%= errorMessage %> </div> <% } %> 
 </div>
 <script>
  document.getElementById('clearButton').addEventListener('click', () => {
  document.getElementById('resultsTable').innerHTML = '';
  document.getElementById('errorMessage').innerHTML = '';
  document.getElementById('successMessage').innerHTML = '';
    });
 </script>
</body>
</html>