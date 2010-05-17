package myml.objectaction.item

import myml.Item;

class CoreItemAction {
	boolean validate(Item i){
		return i.mainCategory == Item.MainCategory.CORE
	}
}
