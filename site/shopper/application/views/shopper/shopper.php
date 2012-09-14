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

		<?=anchor('shopper/logout', 'Logout', 'title="Logout"');?>

		<?php echo form_open('shopper/add') ?>

			<table id="ver-zebra" style="border: 1px black solid;" summary="Most Favorite Movies">
    			<colgroup>
    				<col class="vzebra-odd" style="width: 50px;" />
    				<col class="vzebra-even" />
			    </colgroup>
			    <tbody>
			    	<tr>
			        	<td>Quantity</td>
			            <td>
			            	<?php echo form_error('quantity'); ?>
			            	<input type="text" name="quantity" size="4" value="<?php echo set_value('quantity'); ?>"/>
			            </td>
			        </tr>
			        <tr>
		    	    	<td>Type</td>
			            <td>
            				<select name="type">
							{types}
								<option value="{id}">{name}</option>
							{/types}
							</select>
			            </td>
			        </tr>
			        <tr>
			        	<td>Name</td>
			            <td>
			            	<?php echo form_error('name'); ?>
			            	<input type="text" name="name" value="<?php echo set_value('name'); ?>"/>
			            </td>
			        </tr>
			        <tr>
			        	<td>Brand</td>
			            <td>
			            	<?php echo form_error('brand'); ?>
			            	<input type="text" name="brand" size="10" value="<?php echo set_value('username'); ?>" />
			            </td>
			        </tr>
			        <tr>
			        	<td>Note</td>
			            <td><textarea name="note" rows="2" cols="15"></textarea></td>
			        </tr>
			        <tr>
			            <td colspan="2"><input type="submit" value="Aggiungi" /></td>
			        </tr>
			    </tbody>
			</table>

		</form>

	</p>
			
	<p>
	
<table id="gradient-style" summary="Meeting Results">
    <thead>
    	<tr>
        	<th scope="col">Quantity</th>
            <th scope="col">Type</th>
            <th scope="col">Name</th>
            <th scope="col">Brand</th>
            <th scope="col">Bought</th>
            <th scope="col">Note</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tfoot>
    	<tr>
        	<td colspan="7">Give background color to the table cells to achieve seamless transition</td>
        </tr>
    </tfoot>
    <tbody>
    		<? foreach ($list as $item) { ?>
			<tr height="50">
				<td><?=$item['quantity']?></td>
				<td><?=$type[$item['type']]['name']?></td>
				<td><?=$item['name']?></td>
				<td><?=$item['brand_name']?></td>
				<td>
					<? if ($item['bought']!=0) { ?>
						<img width="32" height="32" src="/~mastrogiovannim/shopper/resources/green_check_mark_button_image_500_clr.png"></img>
					<? } ?>
				</td>
				</td>
				<td><?=$item['note']?></td>
				<td>
					<?php echo form_open('shopper/toggle/' . $item['id']) ?>
					<input type="submit" name="toggle" value="<?=($item['bought']==0?'Buy':'Do noy buy')?>" />
					</form>
				</td>
				<td>
					<?php echo form_open('shopper/delete/' . $item['id']) ?>
					<input type="submit" name="toggle" value="Delete" />
					</form>
				</td>
			</tr>
			<? } ?>
    </tbody>
</table>
		
		</p>
		
	</div>

	<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds</p>
	
</div>

</body>
</html>
