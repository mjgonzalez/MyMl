dataSource {
//	pooled = true
	driverClassName = "oracle.jdbc.OracleDriver"
	dialect='org.hibernate.dialect.Oracle9iDialect'
	username = "nsuarez"
	password = "oracle"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
//			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:oracle:thin:@10.0.10.22:1521:desa"
		}
	}
}