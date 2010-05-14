package myml.objectaction


abstract class ObjectAction<K> {
	public abstract boolean validate(K o)
	public abstract void doAction(K o)
}
