package myml.objectaction

import myml.Item


class ModifyItemAction extends ObjectAction<Item>{
	public boolean validate(Item i) {
		return true
	}
	
	public void doAction(Item i) {
		
	}
	
}
