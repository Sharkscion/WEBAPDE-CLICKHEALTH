<?php
	mysql_connect("localhost", "root", "") or die("could not connect");
	
	myysql_select_db("clickhealth") or die("couldn't connect to database");
	
	$input	=	$_REQUEST['data'];
	$input = mysql_real_escape_string(trim($input));
	$sql = "SELECT * FROM Hospital WHERE hospitalName like '%".$input."%'";
	$data = mysql_query($sql);
	$arrcnt = -1;
	$dataArray = array();
	$list = array();
	
	while($temp = mysql_fetch_assoc($data))
	{
		foreach($temp as $key=>$val)
		{
			$temp[$key] = stripslashes($val);
			$arrcnt++;
		}
		$dataArray[$arrcnt] = $temp;
	}
	
	print_r($dataArray);
	
	$list = "<ul class = 'unorganized'>";
	
	foreach($dataArray as $val)
	{
		$list	.= "<li>".$val['hospitalName']."</li>";
	}
	
	$list .= "</ul>";
	
	echo $list;
	
		
?>