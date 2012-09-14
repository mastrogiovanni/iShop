<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Example
 *
 * This is an example of a few basic user interaction methods you could use
 * all done with a hardcoded array.
 *
 * @package		CodeIgniter
 * @subpackage	Rest Server
 * @category	Controller
 * @author		Phil Sturgeon
 * @link		http://philsturgeon.co.uk/code/
*/

// This can be removed if you use __autoload() in config.php OR use Modular Extensions
require APPPATH.'/libraries/REST_Controller.php';

class Items extends REST_Controller
{
	
	function list_get()
    {
        if(!$this->get('username') || !$this->get('password'))
        {
        	$this->response(array('error' => 'specify username and password'), 400);
        	return;
        }
        
		$this->load->model('user_model');
		
		$user = $this->user_model->login($this->get('username'), $this->get('password'));
		
		if (!$user) {
        	$this->response(array('error' => 'user not known or bad credential'), 400);
        	return;
		}
		
		$this->load->model('shopper_model');
		
		$list = $this->shopper_model->get_all($user['id']);
		
		if ($list) 
		{
            $this->response($list, 200); // 200 being the HTTP response code
		}
        else
        {
            $this->response(array('error' => 'User could not be found'), 404);
        }

    }
    
    function itemtoggle_get()
    {
        if(!$this->get('id') && $this->get('id') != 0)
        {
        	$this->response(array('error' => 'id not found'), 400);
        	return;
        }

        if(!$this->get('username') || !$this->get('password'))
        {
        	$this->response(array('error' => 'specify username and password'), 400);
        	return;
        }

		$this->load->model('user_model');
		
		$user = $this->user_model->login($this->get('username'), $this->get('password'));
		
		if (!$user) {
        	$this->response(array('error' => 'user not known or bad credential'), 400);
        	return;
		}
		
		$this->load->model('shopper_model');
		
		$item = $this->shopper_model->get($this->get('id'));

		if (!$item) 
		{
        	$this->response(array('error' => 'item not found'), 400);
        	return;
		}
		
		$item = $item->row_array();
		
		if ($user['id'] != $item['id_user'])
		{
        	$this->response(array('error' => 'item does not belong to user'), 400);
        	return;
		}
        
		$this->shopper_model->toggle_bought($this->get('id'));
    
        $this->response(array('message' => 'OK'), 200); // 200 being the HTTP response code
    }
    
    /*
    function user_post()
    {
        //$this->some_model->updateUser( $this->get('id') );
        $message = array('id' => $this->get('id'), 'name' => $this->post('name'), 'email' => $this->post('email'), 'message' => 'ADDED!');
        
        $this->response($message, 200); // 200 being the HTTP response code
    }
    
    function user_delete()
    {
    	//$this->some_model->deletesomething( $this->get('id') );
        $message = array('id' => $this->get('id'), 'message' => 'DELETED!');
        
        $this->response($message, 200); // 200 being the HTTP response code
    }
    
    function users_get()
    {
        //$users = $this->some_model->getSomething( $this->get('limit') );
        $users = array(
			array('id' => 1, 'name' => 'Some Guy', 'email' => 'example1@example.com'),
			array('id' => 2, 'name' => 'Person Face', 'email' => 'example2@example.com'),
			3 => array('id' => 3, 'name' => 'Scotty', 'email' => 'example3@example.com', 'fact' => array('hobbies' => array('fartings', 'bikes'))),
		);
        
        if($users)
        {
            $this->response($users, 200); // 200 being the HTTP response code
        }

        else
        {
            $this->response(array('error' => 'Couldn\'t find any users!'), 404);
        }
    }


	public function send_post()
	{
		var_dump($this->request->body);
	}


	public function send_put()
	{
		var_dump($this->put('foo'));
	}
	*/
	
}