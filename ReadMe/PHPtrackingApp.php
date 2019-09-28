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