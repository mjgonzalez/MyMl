package myml.objectaction

import groovy.util.NodeBuilder
import groovy.util.ObjectGraphBuilder
import myml.objectaction.item.*
import myml.Item

class ObjectActionsManager {
	Collection getItemActions(Item item){
		def builder = new NodeBuilder()
		
		def actionsConfig = builder.actions{
			action(val:new ActiveItemAction()){
				action(val:new ModifyItemAction())
			}
		}
		
		def actions = []
		
		collectActions(item, actionsConfig.action, actions)

		return actions
	}

	void collectActions(object, objectActions, collectedActions){
		objectActions.each{
			if(it.'@val'.validate(object)){
				collectActions(object, it, collectedActions)
			}
		}
		if(objectActions.action.isEmpty()){
			collectedActions.add(objectActions.'@val')
		}
	}
	
}
