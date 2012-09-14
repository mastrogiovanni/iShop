<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		
		$this->load->helper('form');
		// $this->load->library('form_validation');
	}
	
	public function index()
	{		
		$this->load->view('shopper/login');
	}
	
	public function api()
	{
		$this->load->view('api/api');
	}

	public function checkin() 
	{
		$this->load->model('user_model');
		
		$user = $this->user_model->login($_POST['username'], $_POST['password']);
		
		if (!$user) {
			$this->load->view('shopper/login');
		}
		else {
			$this->load->library('session');
			$this->load->helper('url');
			$this->session->set_userdata('user_id', $user['id']);
			redirect('/shopper/index', 'refresh');
		}
	}
	
	public function toggle($id, $user_id)
	{
		$this->load->model('shopper_model');
		
		$item = $this->shopper_model->get($id);

		if (!$item) 
		{
			$this->load->library('parser');

			$data['list'] = $this->shopper_model->get_all($user_id)->result();
			$data['id'] = $user_id;
			$this->parser->parse('shopper/shopper', $data);
			
        	return;
		}
		
		$this->shopper_model->toggle_bought($id);
		
		$data['list'] = $this->shopper_model->get_all($user_id)->result();
		$data['id'] = $user_id;
		$this->parser->parse('shopper/shopper', $data);
	
	}
	
	public function register()
	{
		$this->form_validation->set_rules('username', 'Username', 'required');
		$this->form_validation->set_rules('password', 'Password', 'required');
	
		if ($this->form_validation->run() === FALSE)
		{
			$this->load->view('shopper/login');
		}
		else
		{
			$row = $this->user_model->create_user();
			$this->load->view('shopper/created');
		}
		
	}
	
}
