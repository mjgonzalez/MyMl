package myml.objectaction.item

import myml.Item;

class MotorsItemAction {
	boolean validate(Item i){
		return i.mainCategory == Item.MainCategory.MOTORS
	}
}
