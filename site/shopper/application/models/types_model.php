<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Types_model extends CI_Model {
	
	function __construct()
    {
        parent::__construct();
		$this->load->database();
    }
    
    function get_all()
    {
		$this->db->from('type');
		return $this->db->get()->result_array();
    }
    	
}
