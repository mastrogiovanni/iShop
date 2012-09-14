<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Shopper_model extends CI_Model {

    var $id_user = '';
    var $name = '';
    var $quantity = '';
    var $bought = '';
    var $note = '';
	var $order = '';
	var $type = '';
	var $brand = '';
	
	function __construct()
    {
        parent::__construct();
		$this->load->database();
    }

	function get_all($id_user)
	{
		$this->db->select('items.*, brand.name as brand_name');
		$this->db->from('items');
		$this->db->where('items.id_user', $id_user);
		$this->db->join('brand', 'brand.id = items.brand');
		
		return $this->db->get()->result_array();
	}

	/*	
	function exchange_items($id1, $id2)
	{
		$item1 = $this->get($id1);
		if (!$item1)
			return;
			
		$item2 = $this->get($id2);
		if (!$item2)
			return;
			
		$order = $item2['order'];
		$item2['order'] = $item1['order'];
		$item1['order'] = $order;

		$this->db->where('id', $item1['id']);
		$this->db->update('items', $item1); 

		$this->db->where('id', $item2['id']);
		$this->db->update('items', $item2); 
	}
	*/

	function get($id)
	{
		$this->db->from('items')->where('id', $id);
		
		return $this->db->get()->row_array();
	}
	
	function add_item($user_id, $name, $quantity, $type, $note, $brand_id)
	{
		// Look for max order
		$this->db->select_max('order', 'o');
		$this->db->where('id_user', $user_id);
		$query = $this->db->get('items');
		$row = $query->row_array();
		$max_order = $row['o'] + 1;
	
        $this->id_user = $user_id;
        $this->name = $name;
        $this->quantity = $quantity;
        $this->bought = 1;
        $this->note = $note;
        $this->order = $max_order;
        $this->type = $type;
        $this->brand = $brand_id;
                
        $this->db->insert('items', $this);
	}
	
	function delete_item($id)
	{
		$this->db->delete('items', array('id' => $id)); 
	}
    
    function update($id, $name, $quantity, $bought)
    {
		$data = array(
			'name' => $name,
			'quantity' => $quantity,
			'bought' => $bought
		);
            
		$this->db->where('id', $id);
		
		$this->db->update('items', $data); 
    }
    
    function toggle_bought($id) 
    {
		$this->db->from('items')->where('id', $id);
		
		$row = $this->db->get()->row_array();
		
		if (!$row)
			return;
					
		$bought = ( $row['bought'] == 0 ? 1 : 0 );
		
		$row['bought'] = $bought;

		$this->db->where('id', $id);
		
		$this->db->update('items', $row); 

    }

    	
}
