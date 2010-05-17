package myml.objectaction

import myml.Item

class ActiveItemAction extends ObjectAction<Item>{
	boolean validate(Item i){
		i.status == "A"
	}
	
	void doAction(Item i){
		return null
	}
}
