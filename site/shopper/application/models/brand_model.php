<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Brand_model extends CI_Model {
	
	function __construct()
    {
        parent::__construct();
		$this->load->database();
    }
    
    function get($str)
    {
		$this->db->from('brand')->like('name', '%' . $name);
		return $this->db->get()->result_array();
    }
    
    function get_by_name($name)
    {
		$this->db->from('brand')->where('name', $name);
		$row = $this->db->get()->row_array();
		if ($row)
			return $row['id'];
		
		$item = array('name' => $name);
        $this->db->insert('brand', $item);
        
        return $this->get_by_name($name);
    }
    
    function get_all()
    {
		$this->db->from('type');
		return $this->db->get()->result_array();
    }
    	
}
