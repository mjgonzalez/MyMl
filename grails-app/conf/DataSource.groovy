hibernate {
	cache.use_second_level_cache=false
	cache.use_query_cache=false
	cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
dataSource {
	driverClassName = "oracle.jdbc.OracleDriver"
	dialect='org.hibernate.dialect.Oracle9iDialect'
	username = "myml"
	password = "oracle"
	dbCreate = "update" // one of 'create', 'create-drop','update'
	url = "jdbc:oracle:thin:@localhost:1521:xe"
	pooled = true
}