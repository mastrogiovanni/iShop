<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to CodeIgniter</title>

	<style type="text/css">
	<!--
	@import url("/~mastrogiovannim/shopper/resources/default.css");
	-->
	</style>

	</style>
	
	<style type="text/css">
	<!--
	@import url("/~mastrogiovannim/shopper/resources/style.css");
	-->
	</style>

</head>
<body>

<div id="container">

	<h1>Benvenuto ad Husband Shopper</h1>
		
	<div id="body">
	
		<p>
			<h2>Please login</h2>

			<?php echo form_open('login/checkin') ?>

			<table id="ver-zebra" style="border: 1px black solid;" summary="Login">
    			<colgroup>
    				<col class="vzebra-odd" />
    				<col class="vzebra-even" />
			    </colgroup>
			    <tbody>
			    	<tr>
			        	<td>Username</td>
			            <td>
			            	<?php echo form_error('username'); ?>
			            	<input type="text" name="username" />
			            </td>
			        </tr>
			        <tr>
		    	    	<td>Password</td>
			            <td>
			            	<?php echo form_error('password'); ?>
			            	<input type="password" name="password" />
			            </td>
			        </tr>
			        <tr>
		    	    	<td colspan="2"><input type="submit" name="OK" /></td>
			        </tr>
			    </tbody>
			</table>

			</form>
			
		</p>

		<p>
			<h2>Register</h2>

			<?php echo form_open('login/register') ?>

			<table id="ver-zebra" style="border: 1px black solid;" summary="Login">
    			<colgroup>
    				<col class="vzebra-odd" />
    				<col class="vzebra-even" />
			    </colgroup>
			    <tbody>
			    	<tr>
			        	<td>Username</td>
			            <td>
			            	<?php echo form_error('username'); ?>
			            	<input type="text" name="username" />
			            </td>
			        </tr>
			        <tr>
		    	    	<td>Password</td>
			            <td>
			            	<?php echo form_error('password'); ?>
			            	<input type="password" name="password" />
			            </td>
			        </tr>
			        <tr>
		    	    	<td colspan="2"><input type="submit" name="OK" /></td>
			        </tr>
			    </tbody>
			</table>

			</form>
		</p>
		
	</div>
	
	<p>
		<a href="api">Developers API</a>
	</p>

	<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds</p>
</div>

</body>
</html>