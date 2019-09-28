# LocationListener_add_Location_Databases


# About LocationListener_ADD_Location_DataBase
In this application  when your Location is Change this application will add your location(Latitude and Longitutde) into database. I have use xampp sever with Mysql database.use PHP to make connection with database.


![alt text](https://github.com/soumitya0/LocationListener_add_Location_Databases/blob/master/ReadMe/GPS_TRACKING_DB.png)<br>


# use 
1.Location Listener 
2.Xampp server
3.MySql
4.PHP

# PHP (CODE)
https://github.com/soumitya0/LocationListener_add_Location_Databases/blob/master/ReadMe/PHPtrackingApp.php
<h1>
<?php
// display the inserted data into web 
$host="127.0.0.1";
$user="root";
$DBpassword="";
$database="trackingapp";
$connect=mysqli_connect($host,$user,$DBpassword,$database);
if(mysqli_connect_errno())
{
	die("error  connect to database  field". mysqli_connect_errno());
	return;
}
//$username=$_GET['username'];  // user input in brower because GET method 
//$password=$_GET['password'];  // user input in brower because GET method 
$querry="insert into tracking(logi,latit,device_id) values ('".$_GET['logi']. "','". $_GET['latit']."',".$_GET['device_id']. ")"; 
$result=mysqli_query($connect,$querry);
if (!$result) 
{
  $output="{'msg':'Fail'}"; 
}else{
 $output="{'msg':'user is added'}";
}
print($output);
mysqli_close($connect);
?>
</h1>

# Authors
 soumitya chauhan  
 github id : <b>soumitya0</b><br>
 gmail id: <b>soumityachauhan@gmail.com<b></br>
