<%@ page import="java.sql.*" %> <%@ page import="ServletsandHelpers.ConnectionHandler" %> 
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");

%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
 <title>CNT 4714 – Project Four – Fall 2023</title>
 <style>
  body{ 
      background:black;
  }       
  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
        
  th, td {
    border: 1px solid yellow;
    padding: 2px;
  }
  
  .header {
    color:white;
    font-weight: bold;
  }
  
  .main-board {
    width: 80%;
    margin: 0 auto;
    border: 2px solid white;
    padding: 10px;
  }
  
  .main-board-title {
    margin-top:-20px;
    text-align: center;
    color:white;
    width:26%;
    background:black;
    font-size: 18px;
    font-weight: bold;
  }    
  
 </style>
</head>

<body>
 <div class="container">
  <div class="row" style="text-align: center; margin-top: 10px">
   <h1 style="color: red; font-weight: bold; font-size: 35px">Welcome to the Fall 2023 Project 04 Enterprise System</h1>
  </div>
  <div class="row" style="text-align: center; margin-top: 10px">
   <h1 style="color: cyan; font-weight: bold; font-size: 42px">Data Entry Application</h1>
  </div>
  <div class="row" style="text-align: center;">
   <p style="color:white; font-size:  18px;">You are connected to the Project 4 Enterprise System database as a <i style="color: red;">data-entry-level</i> user.</p>
  </div>
  <div class="row" style="text-align: center;">
   <p style="color:white; font-size:  18px;">Enter the data values in a form below to add a new record to the corresponding database table.</p>
  </div> <br> <br>
  <h1 class="main-board-title">Suppliers Record Insert</h1>
  <form method="post" action="SuppliersInsertServlet">
   <table>
    <thead>
     <tr class="header">
      <th>snum</th>
      <th>sname</th>
      <th>status</th>
      <th>city</th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td><input type="text" name="snum"></td>
      <td><input type="text" name="sname"></td>
      <td><input type="text" name="status"></td>
      <td><input type="text" name="city"></td>
     </tr>
    </tbody>
   </table> <input type="submit" style="font-size:20px; font-weight:bold; color:green; margin-top:20px; background-color: #006666; margin-left: 20px;" value="Enter Supplier Record Into Database"> <input type="button" onclick="clearSupplierData()" style="font-size:20px; font-weight:bold; color:red; margin-top:20px; margin-left:170px;  background-color: #006666" value="Clear Data and Results">
  </form> <br> <br>
  <h1 class="main-board-title">Parts Record Insert</h1>
  <form method="post" action="PartsInsertServlet">
   <table>
    <thead>
     <tr class="header">
      <th>pnum</th>
      <th>pname</th>
      <th>color</th>
      <th>weight</th>
      <th>city</th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td><input type="text" name="pnum"></td>
      <td><input type="text" name="pname"></td>
      <td><input type="text" name="color"></td>
      <td><input type="text" name="weight"></td>
      <td><input type="text" name="city"></td>
     </tr>
    </tbody>
   </table> <input type="submit" style="font-size:20px; font-weight:bold; color:green; margin-top:20px; background-color: #006666; margin-left: 20px;" value="Enter Part Record Into Database"> <input type="button" onclick="clearPartData()" style="font-size:20px; font-weight:bold; color:red; margin-top:20px; margin-left:170px;  background-color: #006666" value="Clear Data and Results">
  </form> <br> <br>
  <h1 class="main-board-title">Jobs Record Insert</h1>
  <form method="post" action="JobsInsertServlet">
   <table>
    <thead>
     <tr class="header">
      <th>jnum</th>
      <th>jname</th>
      <th>numworkers</th>
      <th>city</th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td><input type="text" name="jnum"></td>
      <td><input type="text" name="jname"></td>
      <td><input type="text" name="numworkers"></td>
      <td><input type="text" name="city"></td>
     </tr>
    </tbody>
   </table> <input type="submit" style="font-size:20px; font-weight:bold; color:green; margin-top:20px; background-color: #006666; margin-left: 20px;" value="Enter Parts Record Into Database"> <input type="button" onclick="clearJobData()" style="font-size:20px; font-weight:bold; color:red; margin-top:20px; margin-left:170px;  background-color: #006666;" value="Clear Data and Results">
  </form> <br> <br>
  <h1 class="main-board-title">Shipments Record Insert</h1>
  <form method="post" action="ShipmentsInsertServlet">
   <table>
    <thead>
     <tr class="header">
      <th>snum</th>
      <th>pnum</th>
      <th>jnum</th>
      <th>quantity</th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td><input type="text" name="snum"></td>
      <td><input type="text" name="pnum"></td>
      <td><input type="text" name="jnum"></td>
      <td><input type="text" name="quantity"></td>
     </tr>
    </tbody>
   </table> <input type="submit" style="font-size:20px; font-weight:bold; color:green; margin-top:20px; background-color:#006666; margin-left: 20px;" value="Enter Supplier Record Into Database"> <input type="button" onclick="clearShipmentData()" style="font-size:20px; font-weight:bold; color:red; margin-top:20px; margin-left:170px;  background-color: #006666;" value="Clear Data and Results">
  </form> <br> <br>
  <hr>
  <div class="row" style="text-align: center;margin-top: 20px">
   <p style="color:white; font-size:  24px; font-weight: bold">Execution Results: </p>
  </div>
  <div class="container"> <%  if (successMessage != null) { %> <div id="successMessage" style="background: #0066ff; color: white; font-size: 20px; font-weight: bold; text-align: center"> <%= successMessage %> </div> <% } else if (errorMessage != null) { %> <div id="errorMessage" style="background: red; color: white; font-size: 20px; font-weight: bold; text-align: center"> <%= errorMessage %> </div> <% } %> </div>
  <script>
   function clearSupplierData() {
     document.getElementsByName("snum")[0].value = "";
     document.getElementsByName("sname")[0].value = "";
     document.getElementsByName("status")[0].value = "";
     document.getElementsByName("city")[0].value = "";
   }
   
   function clearPartData() {
     document.getElementsByName("pnum")[0].value = "";
     document.getElementsByName("pname")[0].value = "";
     document.getElementsByName("color")[0].value = "";
     document.getElementsByName("weight")[0].value = "";
     document.getElementsByName("city")[1].value = "";
   }
   
   function clearJobData() {
     document.getElementsByName("jnum")[0].value = "";
     document.getElementsByName("jname")[0].value = "";
     document.getElementsByName("numworkers")[0].value = "";
     document.getElementsByName("city")[2].value = "";
   }
   
   function clearShipmentData() {
     document.getElementsByName("jnum")[1].value = "";
     document.getElementsByName("pnum")[1].value = "";
     document.getElementsByName("snum")[1].value = "";
     document.getElementsByName("quantity")[0].value = "";
   } 
  </script>
 </div>
</body>

</html>