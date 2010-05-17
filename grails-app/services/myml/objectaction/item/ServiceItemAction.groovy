package myml.objectaction.item

import myml.Item;

class ServiceItemAction {
	boolean validate(Item i){
		return i.mainCategory == Item.MainCategory.SERVICE
	}
}
