<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Upload Test</title>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.progressbar.min.js"></script>
	<script type="text/javascript" src="js/progressBar.js"></script>
	
	<!-- <script type="text/javascript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script> -->
	<link rel="stylesheet" type="text/css" href="webjars/datatables/1.10.4/css/jquery.dataTables.css" />
	<script type="text/javascript" src="webjars/datatables/1.10.4/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<fieldset>
		<legend>Upload your file</legend>
		<form action="./upload" enctype="multipart/form-data" method="post" id="uploadForm" target="target_upload">
			<input type="file" name="upload" />
			<input type="button" value="上传" id="subButton"/>
		</form>
		<span id="uploadprogressbar" class="progressbar"></span>
		<iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
		<div style="display:none">
			<div id="status">Status</div>
			<div id="read">read</div>
			<div id="total">total</div> 
		</div>
	</fieldset>
	
	
	
	<div style="width: 80%; padding: 10px; margin:10px 0px; "> 
		<hr/> 
	</div>
	
	<div style="width: 80%; border: 1px solid #ccc; padding: 10px; border-radius: 6px;">
		<table id="fileTable">
			<thead>
				<tr>
					<td>Name</td>
					<td>Upload On</td>
					<td>Size</td>
					<td>Download</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>