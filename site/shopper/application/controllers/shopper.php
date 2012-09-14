<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Shopper extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		
		$this->load->helper('form');
		
		$this->load->library('session');
		$this->load->helper('url');
		
		$user_id = $this->session->userdata('user_id');
		if (!$user_id)
			redirect('/login/index', 'refresh');
	}
	
	public function logout()
	{
		$this->load->library('session');
		$this->session->unset_userdata('user_id');
		redirect('/login/index', 'refresh');
	}
	
	public function index()
	{
		$this->load->library('parser');
		$this->load->model('shopper_model');
		$this->load->model('types_model');

		$user_id = $this->session->userdata('user_id');
		
		$data['list'] = $this->shopper_model->get_all($user_id);
		$data['types'] = $this->types_model->get_all();
		
		foreach ($data['types'] as $key => $type) {
			$data['type'][$type['id']] = $type;
		}
		
		$data['user_id'] = $user_id;
		
		$this->parser->parse('shopper/shopper', $data);
	}
		
	public function add() 
	{
		$this->load->library('form_validation');

		$this->form_validation->set_error_delimiters('<div class="error">', '</div>');
	
		$this->form_validation->set_rules('quantity', 'Quantity', 'trim|required|xss_clean');
		$this->form_validation->set_rules('name', 'Name', 'trim|required|xss_clean');
		$this->form_validation->set_rules('brand', 'Brand', 'trim|xss_clean');

		if ($this->form_validation->run() == FALSE)
		{
			$this->index();
		}
		else
		{
			$quantity = $_POST['quantity'];
			$type = $_POST['type'];
			$name = $_POST['name'];
			$note = $_POST['note'];
			$brand = $_POST['brand'];
			$user_id = $this->session->userdata('user_id');

			$this->load->model('brand_model');
			$brand_id = $this->brand_model->get_by_name($brand);

			$this->load->model('shopper_model');
			$row = $this->shopper_model->add_item($user_id, $name, $quantity, $type, $note, $brand_id);
		
			redirect('/shopper/index', 'refresh');
		}
	}
	
	public function delete($id)
	{
		$this->load->model('shopper_model');
		
		$this->shopper_model->delete_item($id);

		redirect('/shopper/index', 'refresh');
	}
	
	public function toggle($id)
	{
		$this->load->model('shopper_model');
		
		$user_id = $this->session->userdata('user_id');
		
		$item = $this->shopper_model->get($id);

		if (!$item) 
		{
			redirect('/shopper/index', 'refresh');
		}
				
		$this->shopper_model->toggle_bought($item['id']);
		
		redirect('/shopper/index', 'refresh');

	}
	
}
