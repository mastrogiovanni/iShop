<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class User_model extends CI_Model {

    var $username = '';
    var $password = '';
    var $id = '';
	
	function __construct()
    {
        parent::__construct();
		$this->load->database();
    }
    
    function login($username, $password)
    {
		$this->db->from('user')->where('username', $username)->where('password', $password);
		return $this->db->get()->row_array();
    }

	function create_user()
    {
        $this->username   	= $_POST['username'];
        $this->password   	= $_POST['password'];
        
        $this->db->insert('user', $this);
    }
    	
}
