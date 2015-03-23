<?php
	mysql_connect("localhost", "root", "") or die("could not connect");
	
	myysql_select_db("ajax") or die("couldn't connect to database");
	
	$searchbox	=	$_REQUEST['searchbox'];
	$searchbox = mysql_real_escape_string(trim($searchbox));
	$sql = "SELECT * FROM Hospital WHERE hospitalName like '%".$searchbox."%'";
	$data = mysql_query($sql);
	$arrcnt = -1;
	$dataArray = array();
	
	while($temp = mysql_fetch_assoc($data))
	{
		foreach($temp as $key=>$val)
		{
			$temp[$key] = stripslashes($val);
			$arrcnt++;
		}
		$dataArray[$arrcnt] = $temp;
	}
	
	$list = "<ul class = 'unorganized'>";

	foreach($dataArray as $val)
	{
		$list	.= "<li>".$val['hospitalName']."</li>";
	}

	$list .= "</ul>";
	
	echo $list;
	
?>