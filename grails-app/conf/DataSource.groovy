hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
//			pooled = true
//			driverClassName = "oracle.jdbc.OracleDriver"
//			dialect='org.hibernate.dialect.Oracle9iDialect'
//			username = "msanfilippo"
//			password = "oracle"
//			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//			url = "jdbc:oracle:thin:@10.0.10.22:1521:desa"
			pooled = true
			driverClassName = "org.hsqldb.jdbcDriver"
			username = "sa"
			password = ""
			dbCreate = "update"
			url = "jdbc:hsqldb:file:testDb"
		}
	}
	
	test {
		dataSource {
			pooled = true
			driverClassName = "org.hsqldb.jdbcDriver"
			username = "sa"
			password = ""
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
}